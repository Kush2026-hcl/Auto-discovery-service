package com.nms.autodiscovery.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Discovery Response")
public class DiscoveryResponse {

    @Schema(example = "Discovery completed successfully")
    private String message;

    @Schema(example = "50")
    private Integer scannedCount;

    @Schema(example = "12")
    private Integer discoveredCount;

    private List<DeviceResponse> devices;

}