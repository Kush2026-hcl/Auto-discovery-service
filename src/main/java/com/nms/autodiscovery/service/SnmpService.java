package com.nms.autodiscovery.service;

import com.nms.autodiscovery.dto.DeviceResponse;

public interface SnmpService {

    /**
     * Discover device using SNMP.
     */
    DeviceResponse discoverDevice(String ipAddress,
                                  String location);

    /**
     * Check whether SNMP is enabled.
     */
    boolean isSnmpEnabled(String ipAddress);

}