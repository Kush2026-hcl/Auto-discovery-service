package com.nms.autodiscovery.service.impl;

import com.nms.autodiscovery.dto.DiscoveryRequest;
import com.nms.autodiscovery.service.DiscoveryService;
import com.nms.autodiscovery.service.SchedulerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchedulerServiceImpl implements SchedulerService {

    private final DiscoveryService discoveryService;

    @Override
    public void executeScheduledDiscovery() {

        log.info("Starting scheduled auto discovery...");

        DiscoveryRequest request = DiscoveryRequest.builder()
                .startIp("192.168.0.1")
                .endIp("192.168.0.254")
                .location("Office")
                .build();

        discoveryService.scanNetwork(request);

        log.info("Scheduled auto discovery completed.");
    }
}