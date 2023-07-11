package com.finalproject.user.controller.user;


import com.finalproject.user.controller.demo.UserRest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Value("${custom.id}")
    private String id;

    @GetMapping("/sayHello")
    public String sayHello(){
        return "user management....id is " + id;
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
    @GetMapping(path = "/{id}")
    public UserRest getUser(@PathVariable String id) {
        System.out.println("has been executed");
        return new UserRest("Sergey", "Kargopolov",id);
    }

}
