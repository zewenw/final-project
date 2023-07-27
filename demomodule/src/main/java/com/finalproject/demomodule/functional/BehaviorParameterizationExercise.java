package com.finalproject.demomodule.functional;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BehaviorParameterizationExercise {

    /**
     * Do Behavior Parameterization for the mapping logic.
     * <p>
     * List<Integer> squaredNumbers =  numbers.stream()
     * .map(x -> x*x)
     * .collect(Collectors.toList());
     */
    public static void main(String[] args) {
        List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15, 25);

        multiAndPrint(numbers, x1 -> x1 * x1);


        multiAndPrint(numbers, x -> x * x * x);

    }

    private static void multiAndPrint(List<Integer> numbers, Function<Integer, Integer> fuction) {
        List<Integer> cubeList = numbers.stream()
                .map(fuction)
                .collect(Collectors.toList());
        System.out.println(cubeList);
    }
}
