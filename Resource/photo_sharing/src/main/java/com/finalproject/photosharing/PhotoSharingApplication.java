package com.finalproject.photosharing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class PhotoSharingApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhotoSharingApplication.class, args);
    }

}
