package com.finalproject.user.service;


import com.finalproject.user.dto.request.UserRequest;
import com.finalproject.user.dto.response.UserResponse;

public interface UserService {

    boolean checkDuplicateUsername(String username);

    UserResponse addUser(UserRequest userRequest);

    UserResponse getUser(String username);

    UserResponse updateUser(UserRequest userRequest);

    void deleteUser(long id);
}
