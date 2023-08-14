package com.finalproject.demomodule.play.thread.lock;

import java.util.concurrent.locks.StampedLock;

public class StampedLockTest {

    private int x = 0;
    private int y = 0;
    private StampedLock lock = new StampedLock();

    public void move(int deltaX, int deltaY) {
        long stamp = lock.writeLock();
        try {
            x += deltaX;
            y += deltaY;
        } finally {
            lock.unlockWrite(stamp);
        }
    }

    public double distanceFromOrigin() {
        long stamp = lock.tryOptimisticRead();
        int currentX = x;
        int currentY = y;
        if (!lock.validate(stamp)) {
            stamp = lock.readLock();
            try {
                currentX = x;
                currentY = y;
            } finally {
                lock.unlockRead(stamp);
            }
        }
        return Math.sqrt(currentX * currentX + currentY * currentY);
    }

    // ...
}
