package com.finalproject.demomodule.functional;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class FunctionalExercise {

    public static void main(String[] args) {
//        List<String> courses = List.of("Spring", "Spring Boot", "API", "Microservices", "AWS", "PCF", "Azure", "Docker", "Kubernetes");
        List<String> courses = List.of("Spring", "Spring Boot");

//        printAllCourse(courses);
//        printContainsSpring(courses);
//        printNameGreaterThan4Letters(courses);
//        printNameCharactersNumber(courses);
        Function<String, Stream<? extends String>> function = new Function<String, Stream<? extends String>>() {
            @Override
            public Stream<? extends String> apply(String item) {
                return Arrays.stream(splitString(item));
            }
        };
        courses.stream()
                .flatMap(function)
                .forEach(System.out::println);
    }
    public static String[] splitString(String name) {
        return name.split("");
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
