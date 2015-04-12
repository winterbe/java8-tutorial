package com.winterbe.java8.samples.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author Benjamin Winterberg
 */
public class Semaphore1 {

    private static final int NUM_INCREMENTS = 10000;

    private static Semaphore semaphore = new Semaphore(1);

    private static int count = 0;

    public static void main(String[] args) {
        testIncrement();
    }

    private static void testIncrement() {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, NUM_INCREMENTS)
                .forEach(i -> executor.submit(Semaphore1::increment));

        ConcurrentUtils.stop(executor);

        System.out.println("Increment: " + count);
    }

    private static void increment() {
        boolean permit = false;
        try {
            permit = semaphore.tryAcquire(5, TimeUnit.SECONDS);
            count++;
        }
        catch (InterruptedException e) {
            throw new RuntimeException("could not increment");
        }
        finally {
            if (permit) {
                semaphore.release();
            }
        }
    }

}
