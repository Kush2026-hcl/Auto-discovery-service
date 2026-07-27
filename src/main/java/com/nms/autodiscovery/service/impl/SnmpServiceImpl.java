package com.nms.autodiscovery.service.impl;

import com.nms.autodiscovery.dto.DeviceResponse;
import com.nms.autodiscovery.service.SnmpService;
import lombok.extern.slf4j.Slf4j;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SnmpServiceImpl implements SnmpService {

    private static final String COMMUNITY = "public";
    private static final int PORT = 161;
    private static final int TIMEOUT = 3000;
    private static final int RETRIES = 2;

    private static final String SYS_DESCR = "1.3.6.1.2.1.1.1.0";
    private static final String SYS_OBJECT_ID = "1.3.6.1.2.1.1.2.0";
    private static final String SYS_NAME = "1.3.6.1.2.1.1.5.0";

    @Override
    public DeviceResponse discoverDevice(String ipAddress, String location) {

        DeviceResponse response = DeviceResponse.builder()
                .ipAddress(ipAddress)
                .location(location)
                .build();

        try {

            String sysDescr = getSnmpValue(ipAddress, SYS_DESCR);
            String sysObjectId = getSnmpValue(ipAddress, SYS_OBJECT_ID);
            String hostName = getSnmpValue(ipAddress, SYS_NAME);

            if (sysDescr == null && sysObjectId == null && hostName == null) {

                response.setStatus("SNMP_NOT_AVAILABLE");
                response.setSnmpVersion(null);
                response.setHostName(null);
                response.setSysDescr(null);
                response.setSysObjectId(null);
                response.setVendor(null);
                response.setDeviceType(null);

                return response;
            }

            response.setStatus("UP");
            response.setSnmpVersion("v2c");
            response.setHostName(hostName);
            response.setSysDescr(sysDescr);
            response.setSysObjectId(sysObjectId);
            response.setVendor(extractVendor(sysDescr));
            response.setDeviceType(extractDeviceType(sysDescr));

            return response;

        } catch (Exception ex) {

            log.error("SNMP discovery failed for {}", ipAddress, ex);

            response.setStatus("SNMP_NOT_AVAILABLE");
            response.setSnmpVersion(null);
            response.setHostName(null);
            response.setSysDescr(null);
            response.setSysObjectId(null);
            response.setVendor(null);
            response.setDeviceType(null);

            return response;
        }
    }

    @Override
    public boolean isSnmpEnabled(String ipAddress) {
        return getSnmpValue(ipAddress, SYS_DESCR) != null;
    }

    private String getSnmpValue(String ipAddress, String oid) {

        Snmp snmp = null;

        try {

            TransportMapping<UdpAddress> transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);
            transport.listen();

            CommunityTarget<Address> target = new CommunityTarget<>();
            target.setCommunity(new OctetString(COMMUNITY));
            target.setAddress(GenericAddress.parse("udp:" + ipAddress + "/" + PORT));
            target.setRetries(RETRIES);
            target.setTimeout(TIMEOUT);
            target.setVersion(SnmpConstants.version2c);

            PDU pdu = new PDU();
            pdu.add(new VariableBinding(new OID(oid)));
            pdu.setType(PDU.GET);

            ResponseEvent<Address> event = snmp.send(pdu, target);

            if (event == null || event.getResponse() == null) {
                return null;
            }

            VariableBinding vb = event.getResponse().get(0);

            if (vb == null || vb.getVariable() == null) {
                return null;
            }

            String value = vb.getVariable().toString();

            if ("Null".equalsIgnoreCase(value)
                    || "noSuchObject".equalsIgnoreCase(value)
                    || "noSuchInstance".equalsIgnoreCase(value)) {
                return null;
            }

            return value;

        } catch (Exception e) {

            return null;

        } finally {

            if (snmp != null) {
                try {
                    snmp.close();
                } catch (Exception ignored) {
                }
            }
        }
    }

    private String extractVendor(String sysDescr) {

        if (sysDescr == null) {
            return null;
        }

        String value = sysDescr.toLowerCase();

        if (value.contains("cisco")) return "Cisco";
        if (value.contains("juniper")) return "Juniper";
        if (value.contains("huawei")) return "Huawei";
        if (value.contains("hp")) return "HP";
        if (value.contains("arista")) return "Arista";
        if (value.contains("windows")) return "Microsoft";
        if (value.contains("linux")) return "Linux";
        if (value.contains("vmware")) return "VMware";

        return "Unknown";
    }

    private String extractDeviceType(String sysDescr) {

        if (sysDescr == null) {
            return null;
        }

        String value = sysDescr.toLowerCase();

        if (value.contains("router")) return "Router";
        if (value.contains("switch")) return "Switch";
        if (value.contains("firewall")) return "Firewall";
        if (value.contains("vmware")) return "Hypervisor";
        if (value.contains("windows")) return "Windows Server";
        if (value.contains("linux")) return "Linux Server";
        if (value.contains("server")) return "Server";

        return "Network Device";
    }
}