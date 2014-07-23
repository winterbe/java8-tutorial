package com.winterbe.java8;

import java.util.Arrays;
import java.util.List;

/**
 * @author Benjamin Winterberg
 */
public class Streams12 {

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("a1", "a2", "b1", "c2", "c1");

//        test1(strings);
//        test2(strings);
        test3(strings);
    }

    private static void test3(List<String> strings) {
        strings
            .parallelStream()
            .filter(s -> {
                System.out.format("filter:  %s [%s]\n", s, Thread.currentThread().getName());
                return true;
            })
            .map(s -> {
                System.out.format("map:     %s [%s]\n", s, Thread.currentThread().getName());
                return s.toUpperCase();
            })
            .sorted((s1, s2) -> {
                System.out.format("sort:    %s <> %s [%s]\n", s1, s2, Thread.currentThread().getName());
                return s1.compareTo(s2);
            })
            .forEach(s -> System.out.format("forEach: %s [%s]\n", s, Thread.currentThread().getName()));
    }

    private static void test2(List<String> strings) {
        strings
            .parallelStream()
            .filter(s -> {
                System.out.format("filter:  %s [%s]\n", s, Thread.currentThread().getName());
                return true;
            })
            .map(s -> {
                System.out.format("map:     %s [%s]\n", s, Thread.currentThread().getName());
                return s.toUpperCase();
            })
            .forEach(s -> System.out.format("forEach: %s [%s]\n", s, Thread.currentThread().getName()));
    }

    private static void test1() {
        // -Djava.util.concurrent.ForkJoinPool.common.parallelism=5

//        ForkJoinPool commonPool = ForkJoinPool.commonPool();
//        System.out.println(commonPool.getParallelism());
    }
}
