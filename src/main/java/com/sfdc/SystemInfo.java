package com.sfdc;

import au.com.bytecode.opencsv.CSVReader;
import org.slf4j.Logger;

import javax.management.*;
import java.io.*;
import java.lang.management.ManagementFactory;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author psrinivasan
 *         Date: 10/13/12
 *         Time: 6:41 PM

 */
public class SystemInfo {

    private static final Logger LOGGER = LoggerFactory.getLogger(SystemInfo.class);

    private static final SystemInfo systemInfo = new SystemInfo();

    private SystemInfo() {
    }

    public static SystemInfo createSystemInfo() {
        return systemInfo;
    }

    public String getOSName() {
        return ManagementFactory.getOperatingSystemMXBean().getName();
    }

    public String getOSVersion() {
        return ManagementFactory.getOperatingSystemMXBean().getVersion();
    }

    public int getMaxFileDescriptorsLinux() throws Exception {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName oName = new ObjectName("java.lang:type=OperatingSystem");
        //AttributeList list = mbs.getAttributes(oName, new String[]{"OpenFileDescriptorCount", "MaxFileDescriptorCount"});
        AttributeList list = mbs.getAttributes(oName, new String[]{"MaxFileDescriptorCount"});
        int size;
        if ( (size = list.size()) != 1) {
            throw new Exception("Expected one JMX attribute, but got " + size);
        }
        Attribute attr = list.asList().get(0);
        if (!attr.getName().equalsIgnoreCase("MaxFileDescriptorCount")) {
             throw new Exception("Obtained an attribute, but it's not called MaxFileDescriptorCount");
        }
        return Integer.parseInt(list.asList().get(0).getValue().toString());
//        System.out.println(list.size());
//        System.out.println("attribute list: " + list.size() + " item(s)");
//        for(Attribute attr: list.asList()){
//            System.out.println(" +- item: " + attr.getName() + " -> " + attr.getValue());
//        }
//
//        return 0;
    }

    public int getMaxFileDescriptors() throws Exception {
        String os = ManagementFactory.getOperatingSystemMXBean().getName();
        if (os.equalsIgnoreCase("Linux")) {
            return getMaxFileDescriptorsLinux();
        }   else if (os.equalsIgnoreCase("Mac OS X")) {
            throw new Exception("SystemInfo not implemented for " + os);
        }
        return 0;
    }

    public int getEphemeralPortCountLinux() throws IOException {
        CSVReader reader = new CSVReader(new FileReader("/proc/sys/net/ipv4/ip_local_port_range"), '\t');
        String [] line;
        int start = 0, end = 0;
        while ((line = reader.readNext()) != null) {
            System.out.println("line is " + line.toString());
            System.out.println("start is " + start);
            System.out.println("end is " + end);
          start = Integer.parseInt(line[0]);
            end = Integer.parseInt(line[1]);

        }
        return end - start;
    }

    public int getEphemeralPortCount() throws Exception {
        String os = ManagementFactory.getOperatingSystemMXBean().getName();
        if (os.equalsIgnoreCase("Linux")) {
            return getEphemeralPortCountLinux();
        }   else if (os.equalsIgnoreCase("Mac OS X")) {
            throw new Exception("SystemInfo not implemented for " + os);
        }
        return 0;
    }
}
