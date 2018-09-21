package com.winterbe.java8.samples.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author Benjamin Winterberg
 */
public class Streams3 {

    public static final int MAX = 1000000;

    public static List<String> getUUIDList(){
        List<String> values = new ArrayList<>(MAX);
        for (int i = 0; i < MAX; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }
        return values;
    }

    public static void sortSequential() {
        List<String> values = getUUIDList();
        // sequential

        long t0 = System.nanoTime();

        long count = values.stream().sorted().count();
        System.out.println(count);

        long t1 = System.nanoTime();

        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("sequential sort took: %d ms", millis));
    }

    public static void sortParallel() {
        List<String> values = getUUIDList();
        // sequential

        long t0 = System.nanoTime();

        long count = values.parallelStream().sorted().count();
        System.out.println(count);

        long t1 = System.nanoTime();

        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("parallel sort took: %d ms", millis));
    }

    /**
     * count values in different thread
     */
    public static void testParallel(){
        List<String> values = getUUIDList();
        Map<String,Integer> threadCount = new ConcurrentHashMap<>();

        values.parallelStream().forEach(s -> {
           String threadName = Thread.currentThread().getName();
            threadCount.putIfAbsent(threadName,1);
            threadCount.computeIfPresent(threadName,(key,value)-> value+1);
        });

        threadCount.forEach((s, integer) -> System.out.println(s+":"+integer));
    }

    public static void main(String[] args) {
        sortSequential();
        sortParallel();
        testParallel();
    }
}
