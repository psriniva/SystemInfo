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

    public void testGetMaxFileDescriptors() throws Exception {
        System.out.println("MaxFileDescriptorCount = " + systemInfo.getMaxFileDescriptors());

    }

    public void testGetOsName() {
        System.out.println(systemInfo.getOSName());
    }

    public void testGetOsVersion() {
        System.out.println(systemInfo.getOSVersion());
    }
}
