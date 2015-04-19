package com.winterbe.java8.samples.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Benjamin Winterberg
 */
public class Lock2 {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        ReentrantLock lock = new ReentrantLock();

        executor.submit(() -> {
            lock.lock();
            try {
                System.out.println(lock.isLocked());
            } finally {
                lock.unlock();
            }
        });

        ConcurrentUtils.stop(executor);
    }

}
