package com.finalproject.photo.controller.resillience;


import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratelimiter")
public class RateLimiterPatternController {


    @GetMapping("/ratelimiter")
    @Retry(name = "rateLimiterForUserApp", fallbackMethod = "rateLimiterFallbackMethod")
    public String sayHello(){
        return "photo component";
    }


    public String rateLimiterFallbackMethod(Throwable t){
        return "photo component and this is the rateLimiterFallbackMethod";
    }
}
