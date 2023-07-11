package com.finalproject.photo.controller;


import com.finalproject.photo.feign.client.UserFeignClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/photo")
public class PhotoController {

    @Autowired
    private UserFeignClient userFeignClient;

    @GetMapping("/sayHello")
    @CircuitBreaker(name = "detailsForUserApp", fallbackMethod = "defaultFallbackMethod")
    public String sayHello(){
        String id = userFeignClient.getId();
        return "photo component and the id is " + id;
    }

    /**
     * fallback method should have same signature with original method and add throwable as a mandatory parameter
     * then the fallback method shouldn't invoke corresponding service in order to avoid failure
     * @param t
     * @return
     */
    public String defaultFallbackMethod(Throwable t){
        return "photo component and this is the default fallback method";
    }
}
