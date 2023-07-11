package com.finalproject.user.controller.user;


import com.finalproject.user.controller.demo.UserRest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/sayHello")
    public String sayHello(){
        return "user management --- users";
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
