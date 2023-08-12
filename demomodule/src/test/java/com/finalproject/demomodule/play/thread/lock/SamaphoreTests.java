package com.finalproject.demomodule.play.thread.lock;

import java.util.concurrent.Semaphore;

public class SamaphoreTests {

    private Semaphore semaphore = new Semaphore(3); // Allow 3 concurrent threads

    public void accessResource() {
        try {
            semaphore.acquire(); // Acquire a permit
            // Access the resource
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release(); // Release the permit
        }
    }

    // ...
}
