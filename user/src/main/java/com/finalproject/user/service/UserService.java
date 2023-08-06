package com.finalproject.user.service;


import com.finalproject.user.dto.request.UserRequest;
import com.finalproject.user.dto.response.UserResponse;

public interface UserService {

    boolean checkDuplicateUsername(String username);

    UserResponse addUser(UserRequest userRequest);
}
