package com.winterbe.java8.samples.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.stream.IntStream;

/**
 * @author Benjamin Winterberg
 */
public class LongAccumulator1 {

    private static final int SIZE = 10;

    private static LongAccumulator accumulator = new LongAccumulator((x, y) -> 2 * x + y, 1L);

    public static void main(String[] args) {
        testAccumulate();
    }

    private static void testAccumulate() {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, SIZE)
                .forEach(i -> executor.submit(() -> accumulator.accumulate(i)));

        ConcurrentUtils.stop(executor);

        System.out.format("Add: %d\n", accumulator.getThenReset());
    }
}
