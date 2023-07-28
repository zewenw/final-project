package com.finalproject.demomodule.functional;

import java.util.stream.LongStream;

public class Parallelizing {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        //175
//        System.out.println(LongStream.range(0, 100000000).sum());
        //43
        System.out.println(LongStream.range(0, 100000000).parallel().sum());
        System.out.println(System.currentTimeMillis() - startTime);
    }
}
