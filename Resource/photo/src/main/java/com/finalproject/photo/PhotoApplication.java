package com.finalproject.photo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@SpringBootApplication
@RefreshScope
@EnableFeignClients
public class PhotoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhotoApplication.class, args);
    }

}
