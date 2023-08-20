package com.finalproject.authserver.service;

import com.finalproject.authserver.feign.client.UserFeignClient;
import com.finalproject.authserver.feign.client.dto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserService implements UserDetailsService {

    @Autowired
    private UserFeignClient userFeignClient;

    //TODO add more logic later
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserResponse response = userFeignClient.getUserByUsername(username);
        User.UserBuilder builder = User.builder();
        UserDetails loginUser = builder.username(response.getUsername())
                .password(response.getPassword())
                .disabled(response.isEnabled())
                .build();
        return loginUser;
    }
}
