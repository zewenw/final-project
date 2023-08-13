package com.finalproject.demomodule.play.collection;

import java.util.Stack;

public class StackTest {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();

        stack.push(10);
        stack.push(20);
        stack.push(30);

        System.out.println("Top element: " + stack.peek()); // Output: Top element: 30

        while (!stack.isEmpty()) {
            System.out.println("Popped element: " + stack.pop());
        }
    }
}
