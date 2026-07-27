package com.nms.autodiscovery.service.impl;


import com.nms.autodiscovery.dto.InventoryDeviceRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class InventoryClientService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${inventory.service.base-url}")
    private String inventoryBaseUrl;

    public boolean registerDevice(InventoryDeviceRequest request) {

        String url = inventoryBaseUrl + "/api/inventory/devices";

        try {
            restTemplate.postForObject(url, request, String.class);
            return true;
        } catch (Exception ex) {
            System.out.println("Inventory registration failed for IP: "
                    + request.getIpAddress());
            System.out.println("Reason: " + ex.getMessage());
            return false;
        }
    }
}