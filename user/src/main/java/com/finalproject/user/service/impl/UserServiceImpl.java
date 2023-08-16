package com.finalproject.user.service.impl;

import com.finalproject.user.convert.UserMapper;
import com.finalproject.user.dto.request.UserRequest;
import com.finalproject.user.dto.response.UserResponse;
import com.finalproject.user.entity.User;
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
        return userRepository.findUserByUsername(username) != null;
    }

    @Override
    public UserResponse addUser(UserRequest userRequest) {
        User saveUser = userRepository.save(UserMapper.MAPPER.userRequestToUser(userRequest));
        return UserMapper.MAPPER.userToUserResponse(saveUser);
    }

    @Override
    public UserResponse getUser(String username) {
        User user = userRepository.findUserByUsername(username);
        return UserMapper.MAPPER.userToUserResponse(user);
    }

    @Override
    public UserResponse updateUser(UserRequest userRequest) {
        User currentUser = userRepository.findUserByUsername(userRequest.getUsername());;
        currentUser.setEmail(userRequest.getEmail());
        currentUser.setEnabled(userRequest.isEnabled());
        currentUser.setPassword(userRequest.getPassword());
        User saveUser = userRepository.save(currentUser);
        return UserMapper.MAPPER.userToUserResponse(saveUser);
    }

    @Override
    public void deleteUser(long id) {
        //todo delete role user mapping
        userRepository.deleteById(id);
    }
}
