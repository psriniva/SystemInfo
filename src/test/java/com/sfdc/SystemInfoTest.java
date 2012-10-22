package com.sfdc;

import junit.framework.TestCase;

/**
 * @author psrinivasan
 *         Date: 10/13/12
 *         Time: 6:50 PM
 */
public class SystemInfoTest extends TestCase {
    private SystemInfo systemInfo = SystemInfo.createSystemInfo();
    public void setUp() throws Exception {

    }

    public void tearDown() throws Exception {

    }

    public void testGetMaxFileDescriptors()  {
        try {
            System.out.println("MaxFileDescriptorCount = " + systemInfo.getMaxFileDescriptors());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void testGetOsName() {
        System.out.println("OS Name = " + systemInfo.getOSName());
    }

    public void testGetOsVersion() {
        System.out.println("OS Version = " + systemInfo.getOSVersion());
    }

    public void testEphemeralPortRange()  {
        try {
            System.out.println("Ephemeral port count = " + systemInfo.getEphemeralPortCount());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
