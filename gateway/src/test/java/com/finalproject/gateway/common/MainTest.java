package com.finalproject.gateway.common;

import org.springframework.util.StringUtils;

public class MainTest {

    public static void main(String[] args) {
        String str = "/user/user/sayHello";
        String[] originalParts = StringUtils.tokenizeToStringArray(str, "/");

        StringBuilder newPath = new StringBuilder("/");
        for (int i = 0; i < originalParts.length; i++) {
            if (i >= 1) {
                // only append slash if this is the second part or greater
                if (newPath.length() > 1) {
                    newPath.append('/');
                }
                newPath.append(originalParts[i]);
            }
        }
        System.out.println(newPath);
    }
}
