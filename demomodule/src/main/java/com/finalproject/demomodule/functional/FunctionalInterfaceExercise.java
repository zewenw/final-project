package com.finalproject.demomodule.functional;

import java.util.List;
import java.util.function.BinaryOperator;

public class FunctionalInterfaceExercise {

    /**
     * Find Functional Interface behind the second argument of reduce method.
     * Create an implementation for the Functional Interface.
     * <p>
     * int sum = numbers.stream()
     * .reduce(0, Integer::sum);
     */
    public static void main(String[] args) {
        List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15);

        BinaryOperator<Integer> operator = new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer x, Integer y) {
                return x + y;
            }
        };

        Integer sum = numbers.stream()
                .reduce(0, operator);
        System.out.println(sum);

    }
}
