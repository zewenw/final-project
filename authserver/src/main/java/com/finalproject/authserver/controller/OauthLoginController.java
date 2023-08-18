package com.finalproject.authserver.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OauthLoginController {

    @Value("${client.redirectUrl}")
    private String redirectUrl;

    @RequestMapping("/custom_login")
    public String loginRedirect() {
        return "redirect:" + redirectUrl;
    }

    @RequestMapping("/custom_login_error")
    public String loginErrorRedirect() {
        //TODO login error function
        return "redirect:" + redirectUrl + "?error=invalid username or password";
    }
}
