package com.finalproject.demomodule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DemomoduleApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemomoduleApplication.class, args);
    }

}
