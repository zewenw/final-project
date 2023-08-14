package com.finalproject.demomodule.play.thread.lock;

import lombok.SneakyThrows;

public class NotifyTests {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();

        Thread thread1 = new Thread(new LockRunnable(lock), "thread1");
        Thread thread2 = new Thread(new LockRunnable(lock), "thread2");
        Thread thread3 = new Thread(new NotifyRunnable(lock), "thread3");


        thread1.start();
        Thread.sleep(100);
        thread2.start();
        Thread.sleep(100);
        thread3.start();
    }
}

class LockRunnable implements Runnable {
    private final Object lock;

    public LockRunnable(Object lock) {
        this.lock = lock;
    }

    @SneakyThrows
    @Override
    public void run() {
        synchronized (lock) {
            System.out.println(Thread.currentThread().getName() + " is waiting");
            try {
                lock.wait(); // Wait for notification
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " is awake");
        }
    }
}

class NotifyRunnable implements Runnable {
    private final Object lock;

    public NotifyRunnable(Object lock) {
        this.lock = lock;
    }

    @SneakyThrows
    @Override
    public void run() {
        synchronized (lock) {
            System.out.println(Thread.currentThread().getName() + " is running");
//            lock.notify();
            lock.notifyAll();
            System.out.println(Thread.currentThread().getName() + " is holding the lock");

            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " is exiting the synchronized block");
        }
        System.out.println(Thread.currentThread().getName() + " released the lock");
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName() + " finished after sleep 1 second");
    }
}