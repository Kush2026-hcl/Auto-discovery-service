package com.nms.autodiscovery.util;

import java.util.regex.Pattern;

public final class ValidationUtil {

    private ValidationUtil() {
    }

    private static final Pattern IP_PATTERN = Pattern.compile(
            "^((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])(\\.|$)){4}$"
    );

    public static boolean isValidIp(String ipAddress) {

        if (ipAddress == null || ipAddress.isBlank()) {
            return false;
        }

        return IP_PATTERN.matcher(ipAddress).matches();
    }

    public static void validateIpRange(String startIp, String endIp) {

        if (!isValidIp(startIp)) {
            throw new IllegalArgumentException("Invalid Start IP Address");
        }

        if (!isValidIp(endIp)) {
            throw new IllegalArgumentException("Invalid End IP Address");
        }
    }
}