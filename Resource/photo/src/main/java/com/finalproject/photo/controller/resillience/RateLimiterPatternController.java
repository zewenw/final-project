package com.finalproject.photo.controller.resillience;


import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratelimiter")
public class RateLimiterPatternController {


    @GetMapping("/ratelimiter")
    @RateLimiter(name = "defaultRateLimiter", fallbackMethod = "rateLimiterFallbackMethod")
    public String sayHello(){
        return "photo component";
    }


    public String rateLimiterFallbackMethod(Throwable t){
        return "photo component and this is the rateLimiterFallbackMethod";
    }
}
