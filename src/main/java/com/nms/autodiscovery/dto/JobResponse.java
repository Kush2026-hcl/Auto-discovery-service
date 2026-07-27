package com.nms.autodiscovery.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Discovery Job Details")
public class JobResponse {

    private Long jobId;

    private String jobName;

    private String startIp;

    private String endIp;

    private String location;

    private String status;

    private Integer totalIps;

    private Integer scannedCount;

    private Integer discoveredCount;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

}