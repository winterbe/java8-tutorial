package com.winterbe.java8.samples.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Optimized version of Streams demo.
 */
public class Streams5 {

    private static final String PREFIX = "a";

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("d2", "a2", "b1", "b3", "c");

        basicFilter(strings);
        mapThenFilter(strings);
        filterThenMap(strings);
        optimizedProcessing(strings);
        shortCircuitExample(strings);
        streamReuseFix(strings);
    }

    // ✅ Utility method (reusable logic)
    private static boolean startsWithIgnoreCase(String s) {
        return s.toLowerCase().startsWith(PREFIX);
    }

    // ✅ 1. Basic filter
    private static void basicFilter(List<String> list) {
        list.stream()
            .filter(s -> true)
            .forEach(System.out::println);
    }

    // ✅ 2. Map then filter (less efficient)
    private static void mapThenFilter(List<String> list) {
        list.stream()
            .map(String::toUpperCase)
            .filter(s -> s.startsWith("A"))
            .forEach(System.out::println);
    }

    // ✅ 3. Filter then map (better)
    private static void filterThenMap(List<String> list) {
        list.stream()
            .filter(s -> s.startsWith("a"))
            .map(String::toUpperCase)
            .forEach(System.out::println);
    }

    // ✅ 4. Optimized pipeline (best practice)
    private static void optimizedProcessing(List<String> list) {
        list.stream()
            .filter(Streams5::startsWithIgnoreCase) // filter first
            .sorted()                               // then sort
            .map(String::toUpperCase)              // method reference
            .forEach(System.out::println);
    }

    // ✅ 5. Short-circuit example
    private static void shortCircuitExample(List<String> list) {
        list.stream()
            .map(s -> {
                System.out.println("map: " + s);
                return s.toUpperCase();
            })
            .anyMatch(s -> {
                System.out.println("anyMatch: " + s);
                return s.startsWith("A");
            });
    }

    // ❌ Streams cannot be reused — fixed version
    private static void streamReuseFix(List<String> list) {
        createStream(list).anyMatch(s -> true);
        createStream(list).noneMatch(s -> true);
    }

    // ✅ Stream factory method (instead of Supplier)
    private static Stream<String> createStream(List<String> list) {
        return list.stream().filter(s -> s.startsWith(PREFIX));
    }
}