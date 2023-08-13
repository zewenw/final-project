package com.finalproject.demomodule.play.collection;

import java.util.Deque;
import java.util.LinkedList;

public class DequeTest {

    public static void main(String[] args) {
        Deque<String> de = new LinkedList();
        de.push("1");
        de.push("2");
        String pop = de.pop();
        String fir = de.removeFirst();
        System.out.println(pop);
        System.out.println(fir);

    }
}
