package com.finalproject.demomodule.play.primitive;


public class StringTest {

    public static void main(String[] args) {
        String s = "abc";
        String b = s + "";
        String c = " ";
        String d = "abcdefg";

//        System.out.println(b == s);
//        System.out.println(c.trim().isEmpty());
//        System.out.println(d.startsWith("bcd", 1));
//        System.out.println(Integer.MAX_VALUE);
//        System.out.println(Integer.MAX_VALUE + 1);
//        System.out.println(Integer.MIN_VALUE);
//        System.out.println(Integer.MIN_VALUE - 1);
//        System.out.println(Integer.MAX_VALUE / 10);
        char a = 'a';
        System.out.println(a < '0');
        System.out.println(a > '0');
//        System.out.println(Integer.valueOf(2147483647 + 1));
//        System.out.println(convertToIntWithBounds("2147483648"));
    }

    public static int convertToIntWithBounds(String str) {
        try {
            int intValue = Integer.parseInt(str);
            if (intValue > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            } else if (intValue < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
            return intValue;
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format");
            return 0; // Or any default value you prefer
        }
    }
}
