package com.finalproject.authserver.controller;


import com.finalproject.authserver.feign.client.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/demo")
public class AuthDemoController {

    @Autowired
    private UserFeignClient userFeignClient;

    @GetMapping("/test")
    public String loginRedirect() {
        return userFeignClient.getUserByUsername("user").getUsername();
    }
}
