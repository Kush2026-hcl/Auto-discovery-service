package com.nms.autodiscovery.constants;

public final class ApplicationConstants {

    private ApplicationConstants() {
    }

    // Application Information
    public static final String APPLICATION_NAME = "Auto Discovery Service";
    public static final String APPLICATION_VERSION = "1.0.0";

    // Discovery Status
    public static final String STATUS_PENDING = "PENDING";
    public static final String STATUS_IN_PROGRESS = "IN_PROGRESS";
    public static final String STATUS_COMPLETED = "COMPLETED";
    public static final String STATUS_FAILED = "FAILED";
    public static final String STATUS_SUCCESS = "SUCCESS";

    // Device Status
    public static final String DEVICE_UP = "UP";
    public static final String DEVICE_DOWN = "DOWN";

    // Discovery Job
    public static final String JOB_NAME = "AUTO_DISCOVERY";

    // Default Values
    public static final String DEFAULT_LOCATION = "UNKNOWN";
    public static final String DEFAULT_VENDOR = "Unknown";
    public static final String DEFAULT_DEVICE_TYPE = "Network Device";

    // Messages
    public static final String DISCOVERY_STARTED =
            "Discovery Started Successfully";

    public static final String DISCOVERY_COMPLETED =
            "Discovery Completed Successfully";

    public static final String DEVICE_DISCOVERED =
            "Device Discovered Successfully";

    public static final String DEVICE_ALREADY_EXISTS =
            "Device Already Exists";

    public static final String INVALID_IP_RANGE =
            "Invalid IP Range";

    public static final String DEVICE_NOT_FOUND =
            "Device Not Found";

    public static final String JOB_NOT_FOUND =
            "Discovery Job Not Found";
}