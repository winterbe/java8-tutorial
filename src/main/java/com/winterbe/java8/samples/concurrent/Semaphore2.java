package com.winterbe.java8.samples.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author Benjamin Winterberg
 */
public class Semaphore2 {

    private static Semaphore semaphore = new Semaphore(5);

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        IntStream.range(0, 10)
                .forEach(i -> executor.submit(Semaphore2::doWork));

        ConcurrentUtils.stop(executor);
    }

    private static void doWork() {
        boolean permit = false;
        try {
            permit = semaphore.tryAcquire(1, TimeUnit.SECONDS);
            if (permit) {
                System.out.println("Semaphore acquired");
                ConcurrentUtils.sleep(5);
            } else {
                System.out.println("Could not acquire semaphore");
            }
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        } finally {
            if (permit) {
                semaphore.release();
            }
        }
    }

}
