package com.finalproject.demomodule.functional;

import java.util.stream.IntStream;

public class StreamArray {

    public static void main(String[] args) {
        int[] arr = {12, 9, 13, 4, 6, 2, 4, 12, 15};
//        System.out.println(Arrays.stream(arr).sum());
//        IntStream.rangeClosed(1, 50).forEach(System.out::println);

        System.out.println(IntStream.rangeClosed(1, 50).reduce(1, Integer::sum));
//        System.out.println(Stream.of(12, 9, 13, 4, 6, 2, 4, 12, 15).count());
    }
}
