package com.finalproject.demomodule.functional;

import java.util.List;

public class Stream {

    public static void main(String[] args) {
        List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15, 25);
        System.out.println(findMaximum(numbers));
    }

    private static int addList(List<Integer> numbers){
        return numbers.stream()
//                .reduce(0, Stream::add);
//                .reduce(0, (x, y) -> x + y);
                .reduce(0, Integer::sum);
    }

    private static int findMaximum(List<Integer> numbers){
        return numbers.stream()
//                .reduce(0, Stream::add);
//                .reduce(0, (x, y) -> x + y);
                .reduce(0, (x, y) -> x > y ? x : y);
    }


    private static int add(int a, int b) {
        return a + b;
    }

}
