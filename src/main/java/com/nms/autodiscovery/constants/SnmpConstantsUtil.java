package com.nms.autodiscovery.constants;

import org.snmp4j.mp.SnmpConstants;

public final class SnmpConstantsUtil {

    private SnmpConstantsUtil() {
    }

    // SNMP Versions
    public static final int SNMP_V1 = SnmpConstants.version1;
    public static final int SNMP_V2C = SnmpConstants.version2c;
    public static final int SNMP_V3 = SnmpConstants.version3;

    // Default Configuration
    public static final String DEFAULT_COMMUNITY = "public";
    public static final int DEFAULT_PORT = 161;
    public static final int DEFAULT_TIMEOUT = 3000;
    public static final int DEFAULT_RETRIES = 2;

    // Common OIDs
    public static final String SYS_DESCR =
            "1.3.6.1.2.1.1.1.0";

    public static final String SYS_OBJECT_ID =
            "1.3.6.1.2.1.1.2.0";

    public static final String SYS_UPTIME =
            "1.3.6.1.2.1.1.3.0";

    public static final String SYS_CONTACT =
            "1.3.6.1.2.1.1.4.0";

    public static final String SYS_NAME =
            "1.3.6.1.2.1.1.5.0";

    public static final String SYS_LOCATION =
            "1.3.6.1.2.1.1.6.0";

    public static final String SYS_SERVICES =
            "1.3.6.1.2.1.1.7.0";

    public static final String IF_NUMBER =
            "1.3.6.1.2.1.2.1.0";

    public static final String IF_DESCRIPTION =
            "1.3.6.1.2.1.2.2.1.2";

    public static final String IF_ADMIN_STATUS =
            "1.3.6.1.2.1.2.2.1.7";

    public static final String IF_OPER_STATUS =
            "1.3.6.1.2.1.2.2.1.8";
}