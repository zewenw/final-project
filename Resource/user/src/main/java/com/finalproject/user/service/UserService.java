package com.finalproject.user.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Value("${custom.id}")
    private String id;

    public String getId(){
        return id;
    }
}
