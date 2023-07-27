package com.finalproject.demomodule.functional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Stream {

    public static void main(String[] args) {
        List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15, 25);
        List<String> courses = List.of("Spring", "Spring Boot", "Apring Boot", "API", "Microservices", "AWS", "PCF", "Azure", "Docker", "Kubernetes");

//        System.out.println(findMaximum(numbers));
//        distinct(numbers);
//        sort(numbers);
//        comparator(courses);
        collector(numbers);
    }

    private static void collector(List<Integer> numbers) {
        numbers.stream()
                .map(item -> item * item)
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    private static void comparator(List<String> numbers) {
        numbers.stream()
//                .sorted(Comparator.naturalOrder())
//                .sorted(Comparator.reverseOrder())
                .sorted(Comparator.comparing(String::length)
                        .reversed()
                        .thenComparing(Comparator.naturalOrder()))
                .forEach(System.out::println);
    }

    private static void sort(List<Integer> numbers) {
        numbers.stream()
                .sorted()
                .forEach(System.out::println);
    }

    private static void distinct(List<Integer> numbers) {
        numbers.stream()
                .distinct()
                .forEach(System.out::println);
    }

    private static int findMaximum(List<Integer> numbers) {
        return numbers.stream()
//                .reduce(0, Stream::add);
//                .reduce(0, (x, y) -> x + y);
                .reduce(0, (x, y) -> x > y ? x : y);
    }

    private static int addList(List<Integer> numbers) {
        return numbers.stream()
//                .reduce(0, Stream::add);
//                .reduce(0, (x, y) -> x + y);
                .reduce(0, Integer::sum);
    }

    private static int add(int a, int b) {
        return a + b;
    }

}
