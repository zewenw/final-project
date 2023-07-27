package com.finalproject.demomodule.functional;

import java.util.List;

public class Functional {

    public static void main(String[] args) {
        List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15);
//        printAllNumbers(numbers);
//        printEvenNumber(numbers);
//        printOddNumbers(numbers);
//        printSquareNumbers(numbers);
        printCubaNumbers(numbers);
    }

    private static void printAllNumbers(List<Integer> numbers) {
//        numbers.forEach(MethodReference::print);// Method Reference
        numbers.forEach(System.out::println);// Method Reference
    }

    private static void printEvenNumber(List<Integer> numbers) {
//        numbers.forEach(MethodReference::print);// Method Reference
        numbers.stream()
                .filter(item -> item % 2 == 0)//Lambda Expression
                .forEach(System.out::println);// Method Reference
    }

    private static void printOddNumbers(List<Integer> numbers) {
//        numbers.forEach(MethodReference::print);// Method Reference
        numbers.stream()
                .filter(item -> item % 2 != 0)//Lambda Expression
                .forEach(System.out::println);// Method Reference
    }

    private static void printSquareNumbers(List<Integer> numbers) {
//        numbers.forEach(MethodReference::print);// Method Reference
        numbers.stream()
                .filter(item -> item % 2 == 0)//Lambda Expression
                .map(item -> item * item)
                .forEach(System.out::println);// Method Reference
    }

    private static void printCubaNumbers(List<Integer> numbers) {
//        numbers.forEach(MethodReference::print);// Method Reference
        numbers.stream()
                .filter(item -> item % 2 != 0)//Lambda Expression
                .map(item -> item * item * item)
                .forEach(System.out::println);// Method Reference
    }


    private static void print(int number) {
        System.out.println(number);
    }
}
