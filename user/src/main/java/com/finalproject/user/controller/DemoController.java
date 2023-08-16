package com.finalproject.user.controller;

import com.finalproject.user.service.UserService;
import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/demo")
@Slf4j
public class DemoController {

    @Autowired
    private UserService userService;

    @GetMapping("/sayHello")
    @Timed(value = "sayHello", description = "this is the sayHello method")
    public String sayHello() {
        log.info("user component, say hello, method start");
//        String username = UserUtil.getCurrentUsername();
//        log.info("=============current user is {}", username);
//        String id = userService.getId();
        log.info("user component, say hello, method end");
        return "user management....id is 2";
    }

    @GetMapping("/currentuser")
    public String getId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        val principal = authentication.getPrincipal();
        System.out.println(principal);
        return "principal.getId()";
    }

    @GetMapping("/status/check")
//    @Scope("SCOPE_profile")
    public String status() {
        return "Working...";
    }

    //    @PreAuthorize("hasAuthority('ROLE_USER')")
//    @Secured("ROLE_USER")
    @DeleteMapping(path = "/{id}")
    public String deleteUser(@PathVariable String id) {
        return "Deleted user with id " + id;
    }


    //    @PostAuthorize("returnObject.userId == 'aaa'")
//    @GetMapping(path = "/{id}")
//    public UserRest getUser(@PathVariable String id) {
//        System.out.println("has been executed");
//        return new UserRest("Sergey", "Kargopolov", id);
//    }
}
