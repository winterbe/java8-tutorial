package com.winterbe.java8.samples.concurrent;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @author Benjamin Winterberg
 */
public class Atomic1 {

    public final int NUM_INCREMENTS = 1000;

    public AtomicInteger atomicInt = new AtomicInteger(0);
    public Integer normalInt = 0;

    @Test
    public void testUpdate() {
        atomicInt.set(0);

        ExecutorService executor = Executors.newFixedThreadPool(5);

        IntStream.range(0, NUM_INCREMENTS)
                .forEach(i -> {
                    Runnable task = () -> {
                        atomicInt.updateAndGet(n -> n + 2);
                        normalInt += 2;
                    };
                    executor.submit(task);
                });
        ConcurrentUtils.stop(executor);

        System.out.format("Update: %d\n", atomicInt.get());
        System.out.format("Update: %d\n", normalInt);//error
    }

    @Test
    public void testAccumulate() {
        atomicInt.set(0);

        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, NUM_INCREMENTS)
                .forEach(i -> {
                    Runnable task = () ->
                            atomicInt.accumulateAndGet(i, (n, m) -> n + m);
                    executor.submit(task);
                });

        ConcurrentUtils.stop(executor);

        System.out.format("Accumulate: %d\n", atomicInt.get());
    }

    @Test
    public void testIncrement() {
        atomicInt.set(0);

        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, NUM_INCREMENTS)
                .forEach(i -> executor.submit(atomicInt::incrementAndGet));

        ConcurrentUtils.stop(executor);

        System.out.format("Increment: Expected=%d; Is=%d\n", NUM_INCREMENTS, atomicInt.get());
    }

}
