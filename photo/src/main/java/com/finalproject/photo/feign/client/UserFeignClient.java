package com.finalproject.photo.feign.client;

import com.finalproject.photo.feign.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("user")
public interface UserFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/feign/user/id")
    String getId();


    @RequestMapping(method = RequestMethod.GET, value = "/feign/user/username")
    UserResponse getUserByUsername(@RequestParam String username);


}
