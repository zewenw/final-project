package com.finalproject.user.controller.login;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class LoginController {

   @GetMapping("login")
    public String login(){
       log.info("login function");
       return "redirect:http://localhost:5173/#/admin/user";
   }

}
