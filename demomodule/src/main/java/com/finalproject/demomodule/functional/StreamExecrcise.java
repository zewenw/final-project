package com.finalproject.demomodule.functional;

import java.util.List;
import java.util.stream.Collectors;

public class StreamExecrcise {

    public static void main(String[] args) {
        List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15);
        List<String> courses = List.of("Spring", "Spring Boot", "Apring Boot", "API", "Microservices", "AWS", "PCF", "Azure", "Docker", "Kubernetes");

//        System.out.println(exercise7(numbers));
//        System.out.println(exercise8(numbers));
//        System.out.println(exercise9(numbers));
//        exercise10(numbers);
        exercise11(courses);
    }


    /**
     * Exercise 11
     * Create a List with lengths of all course titles.
     */
    private static void exercise11(List<String> courses) {
        courses.stream()
                .map(item -> item + " " + item.length())
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    /**
     * Exercise 10
     * Create a List with Even Numbers Filtered from the Numbers List
     */
    private static void exercise10(List<Integer> numbers) {
        numbers.stream()
                .filter(item -> item % 2 == 0)
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    /**
     * Exercise 9
     * Find Sum of Odd Numbers in a list
     */
    private static int exercise9(List<Integer> numbers) {
        return numbers.stream()
                .filter(item -> item % 2 == 1)
//                .map(item -> item * item *item)
                .reduce(0, Integer::sum);
    }

    /**
     * Exercise 8
     * Cube every number in a list and find the sum of cubes
     */
    private static int exercise8(List<Integer> numbers) {
        return numbers.stream()
                .map(item -> item * item * item)
                .reduce(0, Integer::sum);
    }

    /**
     * Exercise 7
     * Square every number in a list and find the sum of squares
     */
    private static int exercise7(List<Integer> numbers) {
        return numbers.stream()
//                .map(item -> item * item)
//                .reduce(0, Integer::sum);
                .reduce(0, (x, y) -> x * x + y * y);
    }

}
