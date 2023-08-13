package com.finalproject.demomodule.play.collection;

import java.util.Stack;

//Last in First out
public class MyStack {

    //store elements
    private int[] container;

    //current element indices
    private int index = 0;

    //total capacity of this stack
    private int capacity;

    //specify capacity
    public MyStack(int size) {
        container = new int[size];
        this.capacity = size;
    }

    //push to the top
    public void push(int item) {
        if(index == capacity){
            throw new IllegalStateException();
        }
        container[++index] = item;
    }

    //remove and return the element form the top of the stack
    public int pop() {
        return container[index--];
    }

    //return the element at the top of the stack without removing it
    public int peek() {
        return container[index];
    }

    //check whether the stack is empty
    public boolean isEmpty() {
        return index == 0;
    }

    //return the size of the stack
    public int size() {
        return capacity;
    }

    public static void main(String[] args) {
        MyStack myStack = new MyStack(5);
        myStack.push(10);
        myStack.push(20);
        myStack.push(30);

        System.out.println("Top element: " + myStack.peek()); // Output: Top element: 30

        while (!myStack.isEmpty()) {
            System.out.println("Popped element: " + myStack.pop());
        }

        System.out.println("====================");

        Stack stack = new Stack();
        stack.push(10);
        stack.push(20);
        stack.push(30);

        System.out.println("Top element: " + stack.peek()); // Output: Top element: 30

        while (!stack.isEmpty()) {
            System.out.println("Popped element: " + stack.pop());
        }
    }
}
