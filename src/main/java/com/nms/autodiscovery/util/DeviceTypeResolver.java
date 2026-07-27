package com.nms.autodiscovery.util;

public final class DeviceTypeResolver {

    private DeviceTypeResolver() {
    }

    public static String resolveDeviceType(String sysDescr) {

        if (sysDescr == null || sysDescr.isBlank()) {
            return "Unknown";
        }

        String description = sysDescr.toLowerCase();

        if (description.contains("router")) {
            return "Router";
        }

        if (description.contains("switch")) {
            return "Switch";
        }

        if (description.contains("firewall")) {
            return "Firewall";
        }

        if (description.contains("server")) {
            return "Server";
        }

        if (description.contains("wireless")) {
            return "Wireless Controller";
        }

        if (description.contains("access point")) {
            return "Access Point";
        }

        return "Network Device";
    }
}