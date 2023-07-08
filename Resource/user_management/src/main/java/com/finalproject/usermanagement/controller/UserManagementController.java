package com.finalproject.usermanagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserManagementController {

    @GetMapping("/sayHello")
    public String sayHello(){
        return "user management....";
    }

}
