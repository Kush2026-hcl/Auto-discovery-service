package com.nms.autodiscovery.service.impl;

import com.nms.autodiscovery.dto.DeviceResponse;
import com.nms.autodiscovery.dto.DiscoveryRequest;
import com.nms.autodiscovery.dto.DiscoveryResponse;
import com.nms.autodiscovery.dto.JobResponse;
import com.nms.autodiscovery.service.DiscoveryService;
import com.nms.autodiscovery.service.IpScannerService;
import com.nms.autodiscovery.service.SnmpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DiscoveryServiceImpl implements DiscoveryService {

    private final IpScannerService ipScannerService;
    private final SnmpService snmpService;

    @Override
    public DiscoveryResponse scanNetwork(DiscoveryRequest request) {

        log.info("Starting auto discovery from {} to {}",
                request.getStartIp(),
                request.getEndIp());

        // Generate all IPs in the range
        List<String> ipList = ipScannerService.generateIpRange(
                request.getStartIp(),
                request.getEndIp());

        List<DeviceResponse> discoveredDevices = new ArrayList<>();

        for (String ip : ipList) {

            try {

                log.info("Scanning IP: {}", ip);

                DeviceResponse device = snmpService.discoverDevice(
                        ip,
                        request.getLocation());

                // Add only SNMP discovered devices
                if (device != null && "UP".equalsIgnoreCase(device.getStatus())) {

                    discoveredDevices.add(device);

                    log.info("Device discovered successfully: {}", ip);
                } else {

                    log.info("SNMP not available for {}", ip);
                }

            } catch (Exception ex) {

                log.error("Discovery failed for {}", ip, ex);
            }
        }

        return DiscoveryResponse.builder()
                .message("Discovery completed successfully")
                .scannedCount(ipList.size())                    // Total scanned IPs
                .discoveredCount(discoveredDevices.size())      // Only discovered SNMP devices
                .devices(discoveredDevices)                     // Only discovered devices
                .build();
    }

    @Override
    public List<JobResponse> getAllJobs() {
        return Collections.emptyList();
    }

    @Override
    public JobResponse getJobById(Long jobId) {
        throw new UnsupportedOperationException(
                "Database is not used in this service.");
    }

    @Override
    public List<DeviceResponse> getAllDevices() {
        return Collections.emptyList();
    }

    @Override
    public DeviceResponse getDeviceByIpAddress(String ipAddress) {
        throw new UnsupportedOperationException(
                "Database is not used in this service.");
    }

    @Override
    public DiscoveryResponse getDiscoveryResult(Long jobId) {
        throw new UnsupportedOperationException(
                "Database is not used in this service.");
    }
}