package com.finalproject.user.feign.provider;

import com.finalproject.user.dto.response.UserResponse;
import com.finalproject.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feign/user")
public class UserProvider {

    @Autowired
    private UserService userService;

    @GetMapping("/id")
    public String getId(){
        return "1";
    }

    @GetMapping("/username")
    public UserResponse user(@RequestParam String username){
        UserResponse response = userService.getUser(username);
        return response;
    }

}
