package com.nms.autodiscovery.config;

import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class SnmpConfig {

    @Bean
    public TransportMapping<UdpAddress> transportMapping() throws IOException {

        TransportMapping<UdpAddress> transport =
                new DefaultUdpTransportMapping();

        transport.listen();

        return transport;
    }

    @Bean
    public Snmp snmp(TransportMapping<UdpAddress> transportMapping)
            throws IOException {

        return new Snmp(transportMapping);
    }

}