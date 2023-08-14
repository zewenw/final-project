package com.finalproject.demomodule.play.thread.lock;

import java.util.concurrent.atomic.AtomicInteger;

public class SynchronizedTest {

    private AtomicInteger count = new AtomicInteger(0);

    public  void increment() {
        count.incrementAndGet();
    }

    public static void main(String[] args) {
        SynchronizedTest synchronizedTest = new SynchronizedTest();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                synchronizedTest.increment();
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                synchronizedTest.increment();
            }
        });
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(synchronizedTest.count);
    }
}
