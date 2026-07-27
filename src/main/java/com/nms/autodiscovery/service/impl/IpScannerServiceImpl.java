package com.nms.autodiscovery.service.impl;

import com.nms.autodiscovery.service.IpScannerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class IpScannerServiceImpl implements IpScannerService {

    @Override
    public List<String> generateIpRange(String startIp, String endIp) {

        List<String> ipList = new ArrayList<>();

        long start = ipToLong(startIp);
        long end = ipToLong(endIp);

        if (start > end) {
            throw new IllegalArgumentException(
                    "Start IP must be less than or equal to End IP");
        }

        for (long ip = start; ip <= end; ip++) {
            ipList.add(longToIp(ip));
        }

        log.info("Generated {} IP addresses.", ipList.size());

        return ipList;
    }

    @Override
    public boolean isReachable(String ipAddress) {
        // Not used in this implementation.
        return true;
    }

    @Override
    public List<String> scanIpRange(String startIp, String endIp) {
        // Return all IPs instead of filtering by reachability.
        return generateIpRange(startIp, endIp);
    }

    private long ipToLong(String ipAddress) {

        String[] parts = ipAddress.split("\\.");

        long result = 0;

        for (String part : parts) {
            result = (result << 8) + Integer.parseInt(part);
        }

        return result;
    }

    private String longToIp(long ip) {

        return String.format("%d.%d.%d.%d",
                (ip >> 24) & 0xFF,
                (ip >> 16) & 0xFF,
                (ip >> 8) & 0xFF,
                ip & 0xFF);
    }
}