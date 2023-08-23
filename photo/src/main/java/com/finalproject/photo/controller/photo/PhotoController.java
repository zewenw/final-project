package com.finalproject.photo.controller.photo;

import com.finalproject.photo.feign.client.UserFeignClient;
import com.finalproject.photo.feign.dto.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1")
public class PhotoController {



    @Autowired
    private UserFeignClient userFeignClient;

    @GetMapping("/photo/sayHello")
    public String sayHello() {
        log.info("user component, say hello, method start");
        log.info("user component, say hello, method end");
        return "photo component...";
    }

    @GetMapping("/photo/interact/{username}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "interact with user component")
    public ResponseEntity<UserResponse> interact(@PathVariable String username) {
        log.info("call user component API");
        UserResponse userResponse = userFeignClient.getUserByUsername(username);
        log.info("get result from user component");
        return ResponseEntity.ok().body(userResponse);
    }
}
