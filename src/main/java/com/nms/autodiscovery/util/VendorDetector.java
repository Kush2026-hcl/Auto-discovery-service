public class VendorDetector {

    public static String detectVendor(String sysObjectId) {

        if (sysObjectId == null)
            return "Unknown";

        if (sysObjectId.startsWith("1.3.6.1.4.1.9"))
            return "Cisco";

        if (sysObjectId.startsWith("1.3.6.1.4.1.2011"))
            return "Huawei";

        if (sysObjectId.startsWith("1.3.6.1.4.1.311"))
            return "Microsoft";

        if (sysObjectId.startsWith("1.3.6.1.4.1.2636"))
            return "Juniper";

        if (sysObjectId.startsWith("1.3.6.1.4.1.674"))
            return "Dell";

        return "Unknown";
    }
}