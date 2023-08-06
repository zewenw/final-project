package com.finalproject.user.controller;

import com.finalproject.user.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/permission")
@Slf4j
public class PermissionController {

    @Autowired
    private PermissionService permissionService;
}
