package com.winterbe.java8;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author Benjamin Winterberg
 */
public class Streams6 {

    public static void main(String[] args) throws IOException {
//        test1();
//        test2();
//        test3();
//        test4();
//        test5();
//        test6();
//        test7();
        test8();
    }

    private static void test8() throws IOException {
        Path start = Paths.get("/Users/benny/Documents");
        int maxDepth = 5;
        long fileCount =
            Files
                .walk(start, maxDepth)
                .filter(path -> String.valueOf(path).endsWith("xls"))
                .count();
        System.out.format("XLS files found: %s", fileCount);
    }

    private static void test7() throws IOException {
        Path start = Paths.get("/Users/benny/Documents");
        int maxDepth = 5;
        Files
            .find(start, maxDepth, (path, attr) ->
                String.valueOf(path).endsWith("xls"))
            .sorted()
            .forEach(System.out::println);
    }

    private static void test6() throws IOException {
        Files
            .list(Paths.get("/usr"))
            .sorted()
            .forEach(System.out::println);
    }

    private static void test5() throws IOException {
        Files
            .lines(Paths.get("/foo", "bar"))
            .filter(line -> line.startsWith("A"))
            .forEach(System.out::println);
    }

    private static void test4() {
        Stream
            .of(new BigDecimal("1.2"), new BigDecimal("3.7"))
            .mapToDouble(BigDecimal::doubleValue)
            .average()
            .ifPresent(System.out::println);
    }

    private static void test3() {
        IntStream
            .range(0, 10)
            .average()
            .ifPresent(System.out::println);
    }

    private static void test2() {
        IntStream
            .builder()
            .add(1)
            .add(3)
            .add(5)
            .add(7)
            .add(11)
            .build()
            .average()
            .ifPresent(System.out::println);

    }

    private static void test1() {
        int[] ints = {1, 3, 5, 7, 11};
        Arrays
            .stream(ints)
            .average()
            .ifPresent(System.out::println);
    }
}
