package com.nms.autodiscovery.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryDeviceRequest {

    private String hostName;
    private String ipAddress;
    private String vendor;
    private String model;
    private String serialNumber;
    private String macAddress;
    private String deviceType;
    private String osVersion;
    private String location;
    private String status;
}