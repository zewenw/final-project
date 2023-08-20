package com.finalproject.authserver.feign.client;

import com.finalproject.authserver.feign.client.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("user")
public interface UserFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/feign/user/username")
    UserResponse getUserByUsername(@RequestParam String username);

}
