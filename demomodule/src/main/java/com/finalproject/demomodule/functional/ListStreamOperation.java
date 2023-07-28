package com.finalproject.demomodule.functional;

import java.util.ArrayList;
import java.util.List;

public class ListStreamOperation {

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        List<String> courses = List.of("Spring", "Spring Boot", "API", "Microservices", "AWS", "PCF", "Azure", "Docker", "Kubernetes");
//        System.out.println(courses.stream().collect(Collectors.joining("_")));
//        courses.stream()
//                .map(str -> str.split(""))
//                .flatMap(Arrays::stream)
//                .collect(Collectors.toList());
        ArrayList<String> modifiableCourses = new ArrayList<>(courses);
//        modifiableCourses.replaceAll(String::toUpperCase);
        modifiableCourses.removeIf(str -> str.length() < 6);
        modifiableCourses.forEach(System.out::println);
    }
}
