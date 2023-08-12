package com.finalproject.demomodule.play.thread.thread;

public class YieldTest {

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " is running");
            Thread.yield(); // Hint to yield to other threads
            System.out.println(Thread.currentThread().getName() + " is done");
        }, "Thread 1");

        Thread thread2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " is running");
            Thread.yield(); // Hint to yield to other threads
            System.out.println(Thread.currentThread().getName() + " is done");
        }, "Thread 2");

        thread1.start();
        thread2.start();
    }

}

