package com.finalproject.photo.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("user")
public interface UserFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/feign/user/id")
    String getId();


}
