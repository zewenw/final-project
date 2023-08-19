package com.finalproject.authserver.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/oauth_login")
    public String loginRedirect(){
        return "login_page";
    }
}
