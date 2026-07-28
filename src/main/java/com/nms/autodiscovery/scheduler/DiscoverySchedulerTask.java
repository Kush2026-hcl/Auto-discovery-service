package com.nms.autodiscovery.scheduler;

import com.nms.autodiscovery.dto.DiscoveryRequest;
import com.nms.autodiscovery.service.DiscoveryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
@ConditionalOnProperty(
        name = "discovery.scheduler.enabled",
        havingValue = "true",
        matchIfMissing = false
)
public class DiscoverySchedulerTask {

    private final DiscoveryService discoveryService;

    @Scheduled(cron = "${discovery.scheduler.cron}")
    public void executeDiscovery() {

        log.info("========== Auto Discovery Started ==========");

        DiscoveryRequest request = DiscoveryRequest.builder()
                .startIp("192.168.0.1")
                .endIp("192.168.0.254")
                .location("Office")
                .build();

        discoveryService.scanNetwork(request);

        log.info("========== Auto Discovery Completed ==========");
    }
}