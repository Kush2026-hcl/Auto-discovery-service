package com.nms.autodiscovery.util;

import java.util.ArrayList;
import java.util.List;

public final class IpRangeUtil {

    private IpRangeUtil() {
    }

    public static List<String> generateIpRange(String startIp, String endIp) {

        List<String> ipList = new ArrayList<>();

        long start = ipToLong(startIp);
        long end = ipToLong(endIp);

        if (start > end) {
            throw new IllegalArgumentException("Start IP should be less than End IP");
        }

        for (long i = start; i <= end; i++) {
            ipList.add(longToIp(i));
        }

        return ipList;
    }

    public static long ipToLong(String ip) {

        String[] octets = ip.split("\\.");

        long result = 0;

        for (String octet : octets) {
            result = (result << 8) | Integer.parseInt(octet);
        }

        return result;
    }

    public static String longToIp(long ip) {

        return String.format("%d.%d.%d.%d",
                (ip >> 24) & 0xFF,
                (ip >> 16) & 0xFF,
                (ip >> 8) & 0xFF,
                ip & 0xFF);
    }
}