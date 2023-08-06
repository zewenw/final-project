package com.finalproject.user.service.impl;

import com.finalproject.user.repository.PermissionRepository;
import com.finalproject.user.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;
}
