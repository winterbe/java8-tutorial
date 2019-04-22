package com.winterbe.java8.samples.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @author Benjamin Winterberg
 */
public class Atomic1 {

    private static final int NUM_INCREMENTS = 1000;

    private static final AtomicInteger ATOMIC_INT = new AtomicInteger(0);

    public static void main(final String[] args) {
        testIncrement();
        testAccumulate();
        testUpdate();
    }

    private static void testUpdate() {
        ATOMIC_INT.set(0);

        final ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, NUM_INCREMENTS)
                .forEach(i -> {
                    Runnable task = () ->
                            ATOMIC_INT.updateAndGet(n -> n + 2);
                    executor.submit(task);
                });

        ConcurrentUtils.stop(executor);

        System.out.format("Update: %d\n", ATOMIC_INT.get());
    }

    private static void testAccumulate() {
        ATOMIC_INT.set(0);

        final ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, NUM_INCREMENTS)
                .forEach(i -> {
                    Runnable task = () ->
                            ATOMIC_INT.accumulateAndGet(i, Integer::sum);
                    executor.submit(task);
                });

        ConcurrentUtils.stop(executor);

        System.out.format("Accumulate: %d\n", ATOMIC_INT.get());
    }

    private static void testIncrement() {
        ATOMIC_INT.set(0);

        final ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, NUM_INCREMENTS)
                .forEach(i -> executor.submit(ATOMIC_INT::incrementAndGet));

        ConcurrentUtils.stop(executor);

        System.out.format("Increment: Expected=%d; Is=%d\n", NUM_INCREMENTS, ATOMIC_INT.get());
    }

}
