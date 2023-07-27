package com.finalproject.demomodule.functional;

import java.util.List;
import java.util.Random;
import java.util.function.*;

public class FunctionalInterfaces2 {

    @SuppressWarnings("unused")
    public static void main(String[] args) {

        List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15);

        //one input with boolean output
        Predicate<Integer> isEvenPredicate = (Integer x) -> x % 2 == 0;

        //one input and another output can be same type with input or not
        Function<Integer, Integer> squareFunction = x -> x * x;
        Function<Integer, String> stringOutpuFunction = x -> x + " ";

        //one input without output
        Consumer<Integer> sysoutConsumer = System.out::println;

        //two same type inputs and one same type output
        BinaryOperator<Integer> sumBinaryOperator = (x, y) -> x + y;

        //No input > Return Something
        Supplier<Integer> randomIntegerSupplier = () -> {
            Random random = new Random();
            return random.nextInt(1000);
        };

        //System.out.println(randomIntegerSupplier.get());

        //one input and one same type output
        UnaryOperator<Integer> unaryOperator = x -> 3 * x;
        System.out.println(unaryOperator.apply(10));

        //two input can be same type or not with one boolean output
        BiPredicate<Integer, String> biPredicate = (number, str) -> {
            return number<10 && str.length()>5;
        };
        System.out.println(biPredicate.test(10, "in28minutes"));


        BiFunction<Integer, String, String> biFunction = (number,str) -> {
            return number + " " + str;
        };
        System.out.println(biFunction.apply(15, "in28minutes"));


        BiConsumer<Integer, String> biConsumer = (s1,s2) -> {
            System.out.println(s1);
            System.out.println(s2);
        };
        biConsumer.accept(25, "in28Minutes");


        BinaryOperator<Integer> sumBinaryOperator2 = (x, y) -> x + y;

        IntBinaryOperator intBinaryOperator = (x, y) -> x + y;

        //IntBinaryOperator
        //IntConsumer
        //IntFunction
        //IntPredicate
        //IntSupplier
        //IntToDoubleFunction
        //IntToLongFunction
        //IntUnaryOperator

        //Long, Double, Int


        //numbers.stream().filter(isEvenPredicate).map(squareFunction).forEach(sysoutConsumer);

        //int sum = numbers.stream().reduce(0, sumBinaryOperator);
    }
}
