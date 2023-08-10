package com.finalproject.user;

import java.nio.charset.StandardCharsets;

public class MainClass {

    public static void main(String[] args) {
        Integer num1 = new Integer(5);
        Integer num2 = new Integer(5);
        int num3 = 5;

        boolean result1 = num1 == num2;
        boolean result2 = num3 == num2;
//        System.out.println(result1);
//        System.out.println(result2);

        String a = "i";
        String b = new String("i");
        String c = "i";
        String d = a + "";
//        System.out.println(a == b);
//        System.out.println(a == c);
        System.out.println(d == a);
        System.out.println(d);
//        System.out.println(a);
        byte[] bytes = a.getBytes(StandardCharsets.UTF_8);
//        System.out.println(bytes);
    }
}
