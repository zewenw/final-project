package com.finalproject.demomodule.play.thread.thread;

public class ThreadTest {

    public static void main(String[] args) {
        Thread myThread = new Thread(() -> {
            try {
                System.out.println("Thread is running");
                Thread.sleep(5000); // Simulate some work
                System.out.println("Thread has finished");
            } catch (InterruptedException e) {
                System.out.println("Thread was interrupted");
                e.printStackTrace();
            }
        });

        // Start the thread
        myThread.start();

        // Interrupt the thread after a short delay
        try {
            Thread.sleep(1000); // Sleep for 1 second
            myThread.interrupt(); // Interrupt the thread
        } catch (InterruptedException e) {
            System.out.println("Thread was interrupted after call interrupt function");
            e.printStackTrace();
        } finally {
            System.out.println("Thread was interrupted after call interrupt function in finally method");

        }

//        try {
//            // Wait for the thread to complete
//            myThread.join();
//        } catch (InterruptedException e) {
//            System.out.println("Thread was interrupted while waiting");
//            e.printStackTrace();
//        }

        System.out.println("Main thread has finished");
    }
}
