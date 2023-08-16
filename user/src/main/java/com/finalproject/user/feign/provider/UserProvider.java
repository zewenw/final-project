package com.finalproject.user.feign.provider;

import com.finalproject.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feign/user")
public class UserProvider {

    @Autowired
    private UserService userService;

    @GetMapping("/id")
    public String getId(){
        return "1";
    }
}
