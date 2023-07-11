package com.finalproject.photo.controller;


import com.finalproject.photo.feign.client.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/photo")
public class PhotoController {

    @Autowired
    private UserFeignClient userFeignClient;

    @GetMapping("/sayHello")
    public String sayHello(){
        String id = userFeignClient.getId();
        return "user management....id is " + id;
    }
}
