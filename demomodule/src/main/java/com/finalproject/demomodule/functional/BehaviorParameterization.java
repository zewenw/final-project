package com.finalproject.demomodule.functional;

import java.util.List;
import java.util.function.Predicate;

/**
 * pass the logic as a parameter
 */
public class BehaviorParameterization {

    public static void main(String[] args) {
        List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15, 25);

//        filterAndPredicate(numbers, x -> x % 2 == 0);

//        filterAndPredicate(numbers, x -> x % 2 == 1);

        filterAndPredicate(numbers, x -> x % 3 == 0);
    }

    private static void filterAndPredicate(List<Integer> numbers, Predicate<Integer> predicate) {
        numbers.stream()
                .filter(predicate)
                .forEach(System.out::println);
    }
}
