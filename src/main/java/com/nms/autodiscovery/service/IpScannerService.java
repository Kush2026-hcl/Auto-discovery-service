package com.nms.autodiscovery.service;

import java.util.List;

public interface IpScannerService {

    /**
     * Generate IP addresses between start and end IP.
     */
    List<String> generateIpRange(String startIp, String endIp);

    /**
     * Check whether IP is reachable.
     */
    boolean isReachable(String ipAddress);

    /**
     * Return all reachable IPs.
     */
    List<String> scanIpRange(String startIp, String endIp);

}