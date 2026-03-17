package com.winterbe.java8.samples.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Optimized version of Streams12.
 */
public class Streams12 {

    public static void main(String[] args) {
        List<String> strings = List.of("a1", "a2", "b1", "c2", "c1");

        showParallelism();
        processSequential(strings);
        processParallel(strings);
        benchmarkSort();
    }

    // ✅ 1. Show available parallelism
    private static void showParallelism() {
        ForkJoinPool pool = ForkJoinPool.commonPool();
        System.out.println("Parallelism level: " + pool.getParallelism());
    }

    // ✅ 2. Sequential processing (baseline)
    private static void processSequential(List<String> strings) {
        List<String> result = strings.stream()
            .filter(s -> s.startsWith("a"))
            .map(String::toUpperCase)
            .sorted()
            .toList();

        System.out.println("Sequential: " + result);
    }

    // ✅ 3. Parallel processing 
    private static void processParallel(List<String> strings) {
        List<String> result = strings.parallelStream()
            .filter(s -> s.startsWith("a"))
            .map(String::toUpperCase)
            .sorted()
            .toList();

        System.out.println("Parallel: " + result);
    }

    // ✅ 4. Proper benchmark 
    private static void benchmarkSort() {
        List<String> values = new ArrayList<>(100_000);

        for (int i = 0; i < 100_000; i++) {
            values.add(UUID.randomUUID().toString());
        }

        // Sequential benchmark
        long t0 = System.nanoTime();

        long count1 = values.stream()
            .sorted()
            .count();

        long t1 = System.nanoTime();

        // Parallel benchmark
        long t2 = System.nanoTime();

        long count2 = values.parallelStream()
            .sorted()
            .count();

        long t3 = System.nanoTime();

        System.out.println("Sequential count: " + count1);
        System.out.println("Parallel count: " + count2);

        System.out.println("Sequential sort took: " +
            TimeUnit.NANOSECONDS.toMillis(t1 - t0) + " ms");

        System.out.println("Parallel sort took: " +
            TimeUnit.NANOSECONDS.toMillis(t3 - t2) + " ms");
    }
}