package com.finalproject.demomodule.play.thread.thread;

public class SleepTests {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println("Thread is running");
            try {
                Thread.sleep(2000); // Pause for 2 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread has finished sleeping");
        });

        thread.start();
    }
}
