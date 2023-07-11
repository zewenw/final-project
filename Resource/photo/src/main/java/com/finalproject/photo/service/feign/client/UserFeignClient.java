package com.finalproject.photo.service.feign.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("user")
public class UserFeignClient {
}
