package com.finalproject.user.controller;


import com.finalproject.user.dto.request.UserRequest;
import com.finalproject.user.dto.response.UserResponse;
import com.finalproject.user.exception.UserException;
import com.finalproject.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "add new user")
    public ResponseEntity<UserResponse> addUser(@RequestBody @Valid UserRequest userRequest){
        String username = userRequest.getUsername();
        if(userService.checkDuplicateUsername(username)){
            throw new UserException("DUPLICATE USERNAME");
        }
        return ResponseEntity.ok().body(userService.addUser(userRequest));
    }


}
