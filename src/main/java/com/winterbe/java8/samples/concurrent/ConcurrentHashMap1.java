package com.winterbe.java8.samples.concurrent;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;

/**
 * @author Benjamin Winterberg
 */
public class ConcurrentHashMap1 {

    public static void main(String[] args) {
        System.out.println("Parallelism: " + ForkJoinPool.getCommonPoolParallelism());

        testForEach();
        testSearch();
        testReduce();
    }

    private static void testReduce() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.putIfAbsent("foo", "bar");
        map.putIfAbsent("han", "solo");
        map.putIfAbsent("r2", "d2");
        map.putIfAbsent("c3", "p0");

        String reduced = map.reduce(1, (key, value) -> key + "=" + value,
                (s1, s2) -> s1 + ", " + s2);

        System.out.println(reduced);
    }

    private static void testSearch() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.putIfAbsent("foo", "bar");
        map.putIfAbsent("han", "solo");
        map.putIfAbsent("r2", "d2");
        map.putIfAbsent("c3", "p0");

        System.out.println("\nsearch()\n");

        String result1 = map.search(1, (key, value) -> {
            System.out.println(Thread.currentThread().getName());
            if (key.equals("foo") && value.equals("bar")) {
                return "foobar";
            }
            return null;
        });

        System.out.println(result1);

        System.out.println("\nsearchValues()\n");

        String result2 = map.searchValues(1, value -> {
            System.out.println(Thread.currentThread().getName());
            if (value.length() > 3) {
                return value;
            }
            return null;
        });

        System.out.println(result2);
    }

    private static void testForEach() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.putIfAbsent("foo", "bar");
        map.putIfAbsent("han", "solo");
        map.putIfAbsent("r2", "d2");
        map.putIfAbsent("c3", "p0");

        map.forEach(1, (key, value) -> System.out.printf("key: %s; value: %s; thread: %s\n", key, value, Thread.currentThread().getName()));
//        map.forEach(5, (key, value) -> System.out.printf("key: %s; value: %s; thread: %s\n", key, value, Thread.currentThread().getName()));

        System.out.println(map.mappingCount());
    }

}
