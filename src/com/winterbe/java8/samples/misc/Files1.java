package com.winterbe.java8.samples.misc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Benjamin Winterberg
 */
public class Files1 {

    public static void main(String[] args) throws IOException {
        testWalk();
        testFind();
        testList();
        testLines();
        testReader();
        testWriter();
        testReadWriteLines();
        testReaderLines();
    }

    private static void testReaderLines() throws IOException {
        Path path = Paths.get("res/nashorn1.js");
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            long countPrints = reader
                    .lines()
                    .filter(line -> line.contains("print"))
                    .count();
            System.out.println(countPrints);
        }
    }

    private static void testWriter() throws IOException {
        Path path = Paths.get("res/output.js");
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write("print('Hello World');");
        }
    }

    private static void testReader() throws IOException {
        Path path = Paths.get("res/nashorn1.js");
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            System.out.println(reader.readLine());
        }
    }

    private static void testWalk() throws IOException {
        Path start = Paths.get("");
        int maxDepth = 5;
        try (Stream<Path> stream = Files.walk(start, maxDepth)) {
            String joined = stream
                    .map(String::valueOf)
                    .filter(path -> path.endsWith(".js"))
                    .collect(Collectors.joining("; "));
            System.out.println("walk(): " + joined);
        }
    }

    private static void testFind() throws IOException {
        Path start = Paths.get("");
        int maxDepth = 5;
        try (Stream<Path> stream = Files.find(start, maxDepth, (path, attr) ->
                String.valueOf(path).endsWith(".js"))) {
            String joined = stream
                    .sorted()
                    .map(String::valueOf)
                    .collect(Collectors.joining("; "));
            System.out.println("find(): " + joined);
        }
    }

    private static void testList() throws IOException {
        try (Stream<Path> stream = Files.list(Paths.get(""))) {
            String joined = stream
                    .map(String::valueOf)
                    .filter(path -> !path.startsWith("."))
                    .sorted()
                    .collect(Collectors.joining("; "));
            System.out.println("list(): " + joined);
        }
    }

    private static void testLines() throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get("res/nashorn1.js"))) {
            stream
                    .filter(line -> line.contains("print"))
                    .map(String::trim)
                    .forEach(System.out::println);
        }
    }

    private static void testReadWriteLines() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("res/nashorn1.js"));
        lines.add("print('foobar');");
        Files.write(Paths.get("res", "nashorn1-modified.js"), lines);
    }
}
