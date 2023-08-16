package com.finalproject.user.utils;

import lombok.val;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtil {

    public static void currentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        val principal = authentication.getPrincipal();
        System.out.println(principal);
    }
}
