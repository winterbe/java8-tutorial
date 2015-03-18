package com.winterbe.java8.samples.misc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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
    }

    private static void testWriter() throws IOException {
        try (BufferedWriter writer =
                     Files.newBufferedWriter(Paths.get("res", "output.js"))) {
            writer.write("print('Hello World');");
        }
    }

    private static void testReader() throws IOException {
        try (BufferedReader reader =
                     Files.newBufferedReader(Paths.get("res", "nashorn1.js"))) {
            System.out.println(reader.readLine());
        }
    }

    private static void testWalk() throws IOException {
        Path start = Paths.get("/Users/benny/Documents");
        int maxDepth = 5;
        long fileCount = Files
                .walk(start, maxDepth)
                .filter(path -> String.valueOf(path).endsWith("xls"))
                .count();
        System.out.format("XLS files found: %s", fileCount);
    }

    private static void testFind() throws IOException {
        Path start = Paths.get("/Users/benny/Documents");
        int maxDepth = 5;
        Files.find(start, maxDepth, (path, attr) ->
                String.valueOf(path).endsWith("xls"))
                .sorted()
                .forEach(System.out::println);
    }

    private static void testList() throws IOException {
        Files.list(Paths.get("/usr"))
                .sorted()
                .forEach(System.out::println);
    }

    private static void testLines() throws IOException {
        Files.lines(Paths.get("res", "nashorn1.js"))
                .filter(line -> line.contains("print"))
                .forEach(System.out::println);
    }

    private static void testReadWriteLines() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("res", "nashorn1.js"));
        lines.add("print('foobar');");
        Files.write(Paths.get("res", "nashorn1-modified.js"), lines);
    }
}
