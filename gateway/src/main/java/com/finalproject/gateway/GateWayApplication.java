package com.finalproject.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;

@EnableDiscoveryClient
@SpringBootApplication
@EnableWebFluxSecurity
@RefreshScope
public class GateWayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GateWayApplication.class, args);
    }

}
