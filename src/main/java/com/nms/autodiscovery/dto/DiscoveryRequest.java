package com.nms.autodiscovery.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Discovery Request")
public class DiscoveryRequest {

    @NotBlank(message = "Start IP is required")
    @Schema(example = "192.168.1.1")
    private String startIp;

    @NotBlank(message = "End IP is required")
    @Schema(example = "192.168.1.50")
    private String endIp;

    @NotBlank(message = "Location is required")
    @Schema(example = "Chennai")
    private String location;
}