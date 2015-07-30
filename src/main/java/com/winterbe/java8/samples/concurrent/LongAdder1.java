package com.winterbe.java8.samples.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

/**
 * @author Benjamin Winterberg
 */
public class LongAdder1 {

    private static final int NUM_INCREMENTS = 10000;

    private static LongAdder adder = new LongAdder();

    public static void main(String[] args) {
        testIncrement();
        testAdd();
    }

    private static void testAdd() {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, NUM_INCREMENTS)
                .forEach(i -> executor.submit(() -> adder.add(2)));

        ConcurrentUtils.stop(executor);

        System.out.format("Add: %d\n", adder.sumThenReset());
    }

    private static void testIncrement() {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, NUM_INCREMENTS)
                .forEach(i -> executor.submit(adder::increment));

        ConcurrentUtils.stop(executor);

        System.out.format("Increment: Expected=%d; Is=%d\n", NUM_INCREMENTS, adder.sumThenReset());
    }
}
