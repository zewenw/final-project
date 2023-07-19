package com.finalproject.photo.controller.resillience;


import com.finalproject.photo.feign.client.UserFeignClient;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/retry")
public class RetryPatternController {

    @Autowired
    private UserFeignClient userFeignClient;

    @GetMapping("/retry")
    @Retry(name = "retryForUserApp", fallbackMethod = "retryFallbackMethod")
    public String sayHello(){
        String id = userFeignClient.getId();
        return "photo component and the id is " + id;
    }


    public String retryFallbackMethod(Throwable t){
        return "photo component and this is the default fallback method";
    }
}
