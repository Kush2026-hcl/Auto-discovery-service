package com.nms.autodiscovery.service;

import com.nms.autodiscovery.dto.DeviceResponse;
import com.nms.autodiscovery.dto.DiscoveryRequest;
import com.nms.autodiscovery.dto.DiscoveryResponse;
import com.nms.autodiscovery.dto.JobResponse;

import java.util.List;

public interface DiscoveryService {

    DiscoveryResponse scanNetwork(DiscoveryRequest request);

    List<JobResponse> getAllJobs();

    JobResponse getJobById(Long jobId);

    List<DeviceResponse> getAllDevices();

    DeviceResponse getDeviceByIpAddress(String ipAddress);

    DiscoveryResponse getDiscoveryResult(Long jobId);

}