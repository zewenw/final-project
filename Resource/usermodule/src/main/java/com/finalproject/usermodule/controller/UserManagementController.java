package com.finalproject.usermodule.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserManagementController {

    @Value("${custom.id}")
    private String id;

    @GetMapping("/sayHello")
    public String sayHello(){
        return "user management....id is " + id;
    }

}
