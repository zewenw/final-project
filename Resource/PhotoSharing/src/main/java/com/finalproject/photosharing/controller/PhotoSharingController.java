package com.finalproject.photosharing.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/photo")
public class PhotoSharingController {

    @GetMapping("/sayHello")
    public String sayHello(){
        return "photo sharing...";
    }
}
