package com.finalproject.photo.controller.photo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/photo")
public class PhotoController {

    private static final Logger logger = LoggerFactory.getLogger(PhotoController.class);


    @GetMapping("/sayHello")
    public String sayHello() {
        logger.info("user component, say hello, method start");
        logger.info("user component, say hello, method end");
        return "photo component...";
    }
}
