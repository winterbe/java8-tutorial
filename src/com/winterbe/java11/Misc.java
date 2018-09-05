package com.winterbe.java11;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Misc {

    @Deprecated(forRemoval = true)
    String foo;

    public static void main(String[] args) throws IOException {
        collections();
        strings();
        optionals();
        inputStreams();
        streams();
    }

    private static void streams() {
        System.out.println(Stream.ofNullable(null).count());   // 0
        System.out.println(Stream.of(1, 2, 3, 2, 1)
                .dropWhile(n -> n < 3)
                .collect(Collectors.toList()));     // [3, 2, 1]
        System.out.println(Stream.of(1, 2, 3, 2, 1)
                .takeWhile(n -> n < 3)
                .collect(Collectors.toList()));     // [1, 2]
    }

    private static void inputStreams() throws IOException {
        var classLoader = ClassLoader.getSystemClassLoader();
        var inputStream = classLoader.getResourceAsStream("com/winterbe/java11/dummy.txt");
        var tempFile = File.createTempFile("dummy-copy", "txt");
        try (var outputStream = new FileOutputStream(tempFile)) {
            inputStream.transferTo(outputStream);
        }
        System.out.println(tempFile.length());
    }

    private static void optionals() {
        System.out.println(Optional.of("foo").orElseThrow());   // foo
        System.out.println(Optional.ofNullable(null).or(() -> Optional.of("bar")).get());   // bar
        System.out.println(Optional.of("foo").stream().count());    // 1
    }

    private static void strings() {
        System.out.println(" ".isBlank());
        System.out.println(" Foo Bar ".strip());    // "Foo Bar"
        System.out.println(" Foo Bar ".stripTrailing());    // " Foo Bar"
        System.out.println(" Foo Bar ".stripLeading());    // "Foo Bar "
        System.out.println("Java".repeat(3));    // "JavaJavaJava"
        System.out.println("A\nB\nC".lines().count());  // 3
    }

    private static void collections() {
        var list = List.of("A", "B", "C");
        var copy = List.copyOf(list);
        System.out.println(list == copy);   // true

        var map = Map.of("A", 1, "B", 2);
        System.out.println(map);
    }

}
