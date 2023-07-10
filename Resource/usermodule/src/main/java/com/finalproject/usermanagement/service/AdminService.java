package com.finalproject.usermanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private JdbcUserDetailsManager jdbcUserDetailsManager;
}
