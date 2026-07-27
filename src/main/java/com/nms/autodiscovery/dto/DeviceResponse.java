package com.nms.autodiscovery.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Discovered Device Details")
public class DeviceResponse {

    @Schema(example = "192.168.1.10")
    private String ipAddress;

    @Schema(example = "Router-01")
    private String hostName;

    @Schema(example = "Cisco IOS XE Software")
    private String sysDescr;

    @Schema(example = "1.3.6.1.4.1.9.1.111")
    private String sysObjectId;

    @Schema(example = "Cisco")
    private String vendor;

    @Schema(example = "Router")
    private String deviceType;

    @Schema(example = "00:11:22:33:44:55")
    private String macAddress;

    @Schema(example = "Chennai")
    private String location;

    @Schema(example = "ACTIVE")
    private String status;

    @Schema(example = "v2c")
    private String snmpVersion;

}