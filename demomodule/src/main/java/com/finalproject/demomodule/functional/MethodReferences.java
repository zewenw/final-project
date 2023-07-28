package com.finalproject.demomodule.functional;

import java.util.List;

public class MethodReferences {

    private static void print(String str){
        System.out.println(str);
    }


    @SuppressWarnings("unused")
    public static void main(String[] args) {
        List<String> courses = List.of("Spring", "Spring Boot", "API", "Microservices", "AWS", "PCF", "Azure", "Docker", "Kubernetes");

        courses.stream()
//                .map(str -> str.toUpperCase())
                .map(String::toUpperCase)
                .forEach(MethodReferences::print);
    }
}
