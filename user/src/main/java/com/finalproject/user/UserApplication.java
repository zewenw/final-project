package com.finalproject.user;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@SpringBootApplication
@RefreshScope
@EnableFeignClients
@OpenAPIDefinition(info =
@Info(title = "User API", version = "1.0", description = "Documentation User API v1.0")
)
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

//    @Bean
//    public TimedAspect timedAspect(MeterRegistry registry) {
//        return new TimedAspect(registry);
//    }

}
