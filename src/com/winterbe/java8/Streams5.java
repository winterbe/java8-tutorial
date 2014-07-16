package com.winterbe.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Testing the order of execution.
 *
 * @author Benjamin Winterberg
 */
public class Streams5 {

    public static void main(String[] args) {
        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");

//        test1(stringCollection);
//        test2(stringCollection);
//        test3(stringCollection);
//        test4(stringCollection);
//        test5(stringCollection);
//        test6(stringCollection);
        test7(stringCollection);
    }

    // stream has already been operated upon or closed
    private static void test7(List<String> stringCollection) {
        Stream<String> stream = stringCollection
            .stream()
            .filter(s -> s.toLowerCase().startsWith("a"));

        stream.anyMatch(s -> true);
        stream.noneMatch(s -> true);
    }

    // short-circuit
    private static void test6(List<String> stringCollection) {
        stringCollection
            .stream()
            .filter(s -> {
                System.out.println("filter: " + s);
                return s.toLowerCase().startsWith("a");
            })
            .map(s -> {
                System.out.println("map: " + s);
                return s.toUpperCase();
            })
            .anyMatch(s -> true);
    }

    private static void test5(List<String> stringCollection) {
        stringCollection
            .stream()
            .filter(s -> {
                System.out.println("filter: " + s);
                return s.toLowerCase().startsWith("a");
            })
            .sorted((s1, s2) -> {
                System.out.printf("sort: %s; %s\n", s1, s2);
                return s1.compareTo(s2);
            })
            .map(s -> {
                System.out.println("map: " + s);
                return s.toUpperCase();
            })
            .forEach(s -> System.out.println("forEach: " + s));
    }

    // sorted = horizontal
    private static void test4(List<String> stringCollection) {
        stringCollection
            .stream()
            .sorted((s1, s2) -> {
                System.out.printf("sort: %s; %s\n", s1, s2);
                return s1.compareTo(s2);
            })
            .filter(s -> {
                System.out.println("filter: " + s);
                return s.toLowerCase().startsWith("a");
            })
            .map(s -> {
                System.out.println("map: " + s);
                return s.toUpperCase();
            })
            .forEach(s -> System.out.println("forEach: " + s));
    }

    private static void test3(List<String> stringCollection) {
        stringCollection
            .stream()
            .filter(s -> {
                System.out.println("filter: " + s);
                return s.toLowerCase().startsWith("a");
            })
            .map(s -> {
                System.out.println("map: " + s);
                return s.toUpperCase();
            })
            .forEach(s -> System.out.println("forEach: " + s));
    }

    private static void test2(List<String> stringCollection) {
        stringCollection
            .stream()
            .map(s -> {
                System.out.println("map: " + s);
                return s.toUpperCase();
            })
            .filter(s -> {
                System.out.println("filter: " + s);
                return s.toLowerCase().startsWith("a");
            })
            .forEach(s -> System.out.println("forEach: " + s));
    }

    private static void test1(List<String> stringCollection) {
        stringCollection
            .stream()
            .filter(s -> {
                System.out.println("filter: " + s);
                return true;
            })
            .forEach(s -> System.out.println("forEach: " + s));
    }

}