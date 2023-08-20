package com.finalproject.authserver.controller;


import com.finalproject.authserver.feign.client.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {




    @RequestMapping("/oauth_login")
    public String loginRedirect(){
        return "login_page";
    }


}
