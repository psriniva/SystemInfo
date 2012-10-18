package com.sfdc;

import org.slf4j.Logger;

import javax.management.*;
import java.lang.management.ManagementFactory;

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

    public int getMaxFileDescriptors() throws Exception {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        System.out.println(ManagementFactory.getOperatingSystemMXBean().getName());
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
}
