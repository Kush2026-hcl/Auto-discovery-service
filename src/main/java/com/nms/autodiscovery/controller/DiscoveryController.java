package com.nms.autodiscovery.controller;

import com.nms.autodiscovery.dto.DeviceResponse;
import com.nms.autodiscovery.dto.DiscoveryRequest;
import com.nms.autodiscovery.dto.DiscoveryResponse;
import com.nms.autodiscovery.dto.JobResponse;
import com.nms.autodiscovery.service.DiscoveryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/discovery")
@RequiredArgsConstructor
@Tag(name = "Auto Discovery", description = "Auto Discovery REST APIs")
public class DiscoveryController {

    private final DiscoveryService discoveryService;

    @Operation(summary = "Scan IP Range",
            description = "Discover SNMP enabled devices within the given IP range.")
    @PostMapping("/scan")
    public ResponseEntity<DiscoveryResponse> scanNetwork(
            @Valid @RequestBody DiscoveryRequest request) {

        DiscoveryResponse response = discoveryService.scanNetwork(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Get All Discovery Jobs")
    @GetMapping("/jobs")
    public ResponseEntity<List<JobResponse>> getAllJobs() {

        return ResponseEntity.ok(discoveryService.getAllJobs());
    }

    @Operation(summary = "Get Discovery Job By Id")
    @GetMapping("/jobs/{jobId}")
    public ResponseEntity<JobResponse> getJobById(
            @PathVariable Long jobId) {

        return ResponseEntity.ok(discoveryService.getJobById(jobId));
    }

    @Operation(summary = "Get All Discovered Devices")
    @GetMapping("/devices")
    public ResponseEntity<List<DeviceResponse>> getAllDevices() {

        return ResponseEntity.ok(discoveryService.getAllDevices());
    }

    @Operation(summary = "Get Device By IP Address")
    @GetMapping("/devices/{ipAddress}")
    public ResponseEntity<DeviceResponse> getDeviceByIpAddress(
            @PathVariable String ipAddress) {

        return ResponseEntity.ok(discoveryService.getDeviceByIpAddress(ipAddress));
    }

    @Operation(summary = "Get Discovery Results By Job Id")
    @GetMapping("/results/{jobId}")
    public ResponseEntity<DiscoveryResponse> getDiscoveryResult(
            @PathVariable Long jobId) {

        return ResponseEntity.ok(discoveryService.getDiscoveryResult(jobId));
    }

}