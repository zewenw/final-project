package com.finalproject.user.service.impl;

import com.finalproject.user.repository.RoleRepository;
import com.finalproject.user.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
}
