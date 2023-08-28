package com.finalproject.user.controller;


import com.finalproject.user.dto.request.UserRequest;
import com.finalproject.user.dto.response.UserResponse;
import com.finalproject.user.component.exception.BusinessException;
import com.finalproject.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


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
        log.info("com.finalproject.user.controller.UserController.addUser, param: {}", userRequest);
        String username = userRequest.getUsername();
        if(userService.checkDuplicateUsername(username)){
            throw new BusinessException("DUPLICATE USERNAME");
        }
        return ResponseEntity.ok().body(userService.addUser(userRequest));
    }

    @GetMapping("/user/{username}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "get user by usename")
    public ResponseEntity<List<UserResponse>> getUser(@PathVariable @NotNull String username){
        log.info("com.finalproject.user.controller.UserController.getUser, param: {}", username);
        List<UserResponse> list = new ArrayList<>();
        list.add(userService.getUser(username));
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "get all users")
    public ResponseEntity<List<UserResponse>> getAllUser(){
        log.info("com.finalproject.user.controller.UserController.getAllUser");
        return ResponseEntity.ok().body(userService.getAllUser());
    }

    @GetMapping("/users/page")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "get users by page")
    public ResponseEntity<Page<UserResponse>> getUserByPage( @Valid UserRequest userRequest){
        log.info("com.finalproject.user.controller.UserController.getUserByPage, param: {}", userRequest);
        return ResponseEntity.ok().body(userService.getUserByPage(userRequest));
    }

    @PutMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "update user info")
    public ResponseEntity<UserResponse> updateUser(@RequestBody @Valid UserRequest userRequest){
        log.info("com.finalproject.user.controller.UserController.updateUser, param: {}", userRequest);
        return ResponseEntity.ok().body(userService.updateUser(userRequest));
    }

    @DeleteMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "delete user by user id")
    public ResponseEntity<String> deleteUser(@PathVariable long id){
        log.info("com.finalproject.user.controller.UserController.deleteUser, param: {}", id);
        userService.deleteUser(id);
        return  ResponseEntity.ok().build();
    }


}
