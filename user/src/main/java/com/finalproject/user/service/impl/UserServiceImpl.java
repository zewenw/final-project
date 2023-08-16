package com.finalproject.user.service.impl;

import com.finalproject.user.dto.request.UserRequest;
import com.finalproject.user.dto.response.UserResponse;
import com.finalproject.user.repository.UserRepository;
import com.finalproject.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean checkDuplicateUsername(String username) {
        return userRepository.findUserByUsername(username) == null;
    }

    @Override
    public UserResponse addUser(UserRequest userRequest) {

        return null;
    }
}
