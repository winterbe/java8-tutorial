package com.winterbe.java8.samples.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @author Benjamin Winterberg
 */
public class Atomic1 {

    private static final int NUM_INCREMENTS = 10000;

    private static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) {
        testIncrement();
    }

    private static void testIncrement() {
        count.set(0);

        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, NUM_INCREMENTS)
                .forEach(i -> executor.submit(count::incrementAndGet));

        ConcurrentUtils.stop(executor);

        System.out.format("Expected=%d; Is=%d", NUM_INCREMENTS, count.get());
    }

}
