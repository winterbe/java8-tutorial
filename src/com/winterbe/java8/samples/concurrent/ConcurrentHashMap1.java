package com.winterbe.java8.samples.concurrent;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;

/**
 * @author Benjamin Winterberg
 */
public class ConcurrentHashMap1 {

    public static void main(String[] args) {
        testForEach();
    }

    private static void testForEach() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.putIfAbsent("foo", "bar");
        map.putIfAbsent("han", "solo");
        map.putIfAbsent("r2", "d2");
        map.putIfAbsent("c3", "p0");


//        map.forEach((key, value) -> System.out.printf("key: %s; value: %s\n", key, value));

        System.out.println("Parallelism: " + ForkJoinPool.getCommonPoolParallelism());

        map.forEach(1, (key, value) -> System.out.printf("key: %s; value: %s; thread: %s\n", key, value, Thread.currentThread().getName()));
//        map.forEach(5, (key, value) -> System.out.printf("key: %s; value: %s; thread: %s\n", key, value, Thread.currentThread().getName()));

        System.out.println(map.mappingCount());
    }

}
