package com.winterbe.java8.samples.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/**
 * @author Benjamin Winterberg
 */
public class Lock5 {

    private static int count = 0;

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        StampedLock lock = new StampedLock();

        executor.submit(() -> {
            long stamp = lock.tryOptimisticRead();
            try {
                System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
                TimeUnit.SECONDS.sleep(1);
                System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            } finally {
                lock.unlock(stamp);
            }
        });

        executor.submit(() -> {
            long stamp = lock.writeLock();
            try {
                System.out.println("Write Lock acquired");
                incrementAndSleep(2);
            } finally {
                lock.unlock(stamp);
                System.out.println("Write done");
            }
        });


        ConcurrentUtils.stop(executor);
    }

    private static void incrementAndSleep(int sleepSeconds) {
        try {
            count++;
            TimeUnit.SECONDS.sleep(sleepSeconds);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}
