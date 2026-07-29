package com.nms.autodiscovery.service.impl;

import com.nms.autodiscovery.dto.DeviceResponse;
import com.nms.autodiscovery.dto.DiscoveryRequest;
import com.nms.autodiscovery.dto.DiscoveryResponse;
import com.nms.autodiscovery.dto.InventoryDeviceRequest;
import com.nms.autodiscovery.dto.JobResponse;
import com.nms.autodiscovery.service.DiscoveryService;
import com.nms.autodiscovery.service.IpScannerService;
import com.nms.autodiscovery.service.SnmpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class DiscoveryServiceImpl implements DiscoveryService {

    private final IpScannerService ipScannerService;
    private final SnmpService snmpService;
    private final InventoryClientService inventoryClientService;

    @Override
    public DiscoveryResponse scanNetwork(DiscoveryRequest request) {

        log.info(
                "Starting auto discovery from {} to {}",
                request.getStartIp(),
                request.getEndIp()
        );

        /*
         * Generate IP range.
         */
        List<String> ipList = ipScannerService.generateIpRange(
                request.getStartIp(),
                request.getEndIp()
        );

        /*
         * Scan IP addresses in parallel.
         *
         * Earlier:
         * IP1 -> wait -> IP2 -> wait -> IP3
         *
         * Now:
         * IP1 \
         * IP2  -> run concurrently
         * IP3 /
         */
        List<CompletableFuture<DeviceResponse>> futures =
                ipList.stream()
                        .map(ip -> CompletableFuture.supplyAsync(
                                () -> discoverSingleDevice(
                                        ip,
                                        request.getLocation()
                                )
                        ))
                        .toList();

        /*
         * Wait for parallel discovery tasks and collect
         * only successfully discovered devices.
         */
        List<DeviceResponse> discoveredDevices =
                futures.stream()
                        .map(CompletableFuture::join)
                        .filter(Objects::nonNull)
                        .toList();

        log.info(
                "Auto discovery completed. Scanned: {}, Discovered: {}",
                ipList.size(),
                discoveredDevices.size()
        );

        return DiscoveryResponse.builder()
                .message("Discovery completed successfully")
                .scannedCount(ipList.size())
                .discoveredCount(discoveredDevices.size())
                .devices(discoveredDevices)
                .build();
    }


    /**
     * Discover one IP address.
     */
    private DeviceResponse discoverSingleDevice(
            String ip,
            String location) {

        try {

            log.info("Scanning IP: {}", ip);

            /*
             * SNMP discovery.
             */
            DeviceResponse device =
                    snmpService.discoverDevice(
                            ip,
                            location
                    );

            /*
             * Device must exist and have UP status.
             */
            if (device == null
                    || !"UP".equalsIgnoreCase(device.getStatus())) {

                log.info(
                        "SNMP device not discovered for {}",
                        ip
                );

                return null;
            }

            log.info(
                    "Device discovered successfully: {}",
                    ip
            );

            /*
             * Prepare Inventory Service request.
             */
            InventoryDeviceRequest inventoryRequest =
                    InventoryDeviceRequest.builder()
                            .hostName(device.getHostName())
                            .ipAddress(device.getIpAddress())
                            .vendor(device.getVendor())
                            .model(null)
                            .serialNumber(null)
                            .macAddress(device.getMacAddress())
                            .deviceType(device.getDeviceType())
                            .osVersion(device.getSysDescr())
                            .location(device.getLocation())
                            .status(device.getStatus())
                            .build();

            /*
             * Register device with Inventory Service.
             */
            try {

                boolean success =
                        inventoryClientService.registerDevice(
                                inventoryRequest
                        );

                if (success) {

                    log.info(
                            "Inventory registration successful for {}",
                            device.getIpAddress()
                    );

                } else {

                    log.error(
                            "Inventory registration failed for {}",
                            device.getIpAddress()
                    );
                }

            } catch (Exception ex) {

                /*
                 * Inventory failure should NOT remove
                 * successfully discovered SNMP device.
                 */
                log.error(
                        "Inventory registration failed for {}",
                        device.getIpAddress(),
                        ex
                );
            }

            /*
             * Return successfully discovered device.
             */
            return device;

        } catch (Exception ex) {

            /*
             * Failure of one IP should not stop
             * remaining discovery tasks.
             */
            log.error(
                    "Discovery failed for {}",
                    ip,
                    ex
            );

            return null;
        }
    }


    @Override
    public List<JobResponse> getAllJobs() {

        return Collections.emptyList();
    }


    @Override
    public JobResponse getJobById(Long jobId) {

        throw new UnsupportedOperationException(
                "Database is not used in this service."
        );
    }


    @Override
    public List<DeviceResponse> getAllDevices() {

        return Collections.emptyList();
    }


    @Override
    public DeviceResponse getDeviceByIpAddress(
            String ipAddress) {

        throw new UnsupportedOperationException(
                "Database is not used in this service."
        );
    }


    @Override
    public DiscoveryResponse getDiscoveryResult(
            Long jobId) {

        throw new UnsupportedOperationException(
                "Database is not used in this service."
        );
    }
}