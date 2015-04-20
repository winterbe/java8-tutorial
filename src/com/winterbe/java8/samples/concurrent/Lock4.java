package com.winterbe.java8.samples.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/**
 * @author Benjamin Winterberg
 */
public class Lock4 {

    private static int count = 0;

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        StampedLock lock = new StampedLock();

        executor.submit(() -> {
            long stamp = lock.writeLock();
            try {
                count++;
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            } finally {
                lock.unlockWrite(stamp);
            }
        });

        executor.submit(() -> {
            long stamp = lock.readLock();
            try {
                System.out.println(Thread.currentThread().getName() + ": " + count);
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            } finally {
                lock.unlockRead(stamp);
            }
        });

        executor.submit(() -> {
            long stamp = lock.readLock();
            try {
                System.out.println(Thread.currentThread().getName() + ": " + count);
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            } finally {
                lock.unlockRead(stamp);
            }
        });

        ConcurrentUtils.stop(executor);
    }

}
