package com.finalproject.demomodule.functional;

public class ThreadFunction {

    public static void main(String[] args) {
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 1000; i++){
                    System.out.println(Thread.currentThread().getId() + ":" + i);
                }
            }
        };

        Runnable runnable2 = () ->{
            for(int i = 0; i < 1000; i++){
                System.out.println(Thread.currentThread().getId() + ":" + i);
            }
        };

        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);
        thread1.start();
        thread2.start();
    }
}
