package com.nms.autodiscovery.util;

public final class VendorResolver {

    private VendorResolver() {
    }

    public static String resolveVendor(String sysDescr) {

        if (sysDescr == null || sysDescr.isBlank()) {
            return "Unknown";
        }

        String description = sysDescr.toLowerCase();

        if (description.contains("cisco")) {
            return "Cisco";
        }

        if (description.contains("juniper")) {
            return "Juniper";
        }

        if (description.contains("huawei")) {
            return "Huawei";
        }

        if (description.contains("hp")) {
            return "HP";
        }

        if (description.contains("arista")) {
            return "Arista";
        }

        if (description.contains("fortinet")) {
            return "Fortinet";
        }

        return "Unknown";
    }
}