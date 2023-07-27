package com.finalproject.demomodule.functional;

import java.util.List;

public class FunctionalExercise {

    public static void main(String[] args) {
        List<String> courses = List.of("Spring", "Spring Boot", "API", "Microservices", "AWS", "PCF", "Azure", "Docker", "Kubernetes");
//        printAllCourse(courses);
//        printContainsSpring(courses);
//        printNameGreaterThan4Letters(courses);
        printNameCharactersNumber(courses);
    }

    private static void printAllCourse(List<String> courses) {
        courses.forEach(System.out::println);// Method Reference
    }

    private static void printContainsSpring(List<String> courses) {
        courses.stream()
                .filter(v -> v.contains("Spring"))
                .forEach(System.out::println);// Method Reference
    }

    private static void printNameGreaterThan4Letters(List<String> courses) {
        courses.stream()
                .filter(v -> v.length() > 4)
                .forEach(System.out::println);// Method Reference
    }

    private static void printNameCharactersNumber(List<String> courses) {
        courses.stream()
                .map(item -> item + " : " + + item.length())
                .forEach(System.out::println);// Method Reference
    }


}
