package com.finalproject.user.controller;

import com.finalproject.foundation.utils.UserUtil;
import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/demo")
@Slf4j
public class DemoController {

    @GetMapping("/sayHello")
    @Timed(value = "sayHello", description = "this is the sayHello method")
    public String sayHello() {
        log.info("user component, say hello, method start");
        String username = UserUtil.getCurrentUsername();
        log.info("=============current user is {}", username);
        log.info("user component, say hello, method end");
        return "user management....id is 1";
    }

    @PostMapping("/id")
    public String getId() {
        return "1";
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
