package com.finalproject.demomodule.play.primitive;


public class StringTest {

    public static void main(String[] args) {
        String s = "abc";
        String b = s + "";
        String c = " ";
        String d = "abcdefg";
//        System.out.println(b == s);
//        System.out.println(c.trim().isEmpty());
        System.out.println(d.startsWith("bcd", 1));
    }
}
