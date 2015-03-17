package com.winterbe.java8.samples.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author Benjamin Winterberg
 */
public class Streams7 {

    static class Foo {
        String name;
        List<Bar> bars = new ArrayList<>();

        Foo(String name) {
            this.name = name;
        }
    }

    static class Bar {
        String name;

        Bar(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {
//        test1();
        test2();
    }

    static void test2() {
        IntStream.range(1, 4)
            .mapToObj(num -> new Foo("Foo" + num))
            .peek(f -> IntStream.range(1, 4)
                .mapToObj(num -> new Bar("Bar" + num + " <- " + f.name))
                .forEach(f.bars::add))
            .flatMap(f -> f.bars.stream())
            .forEach(b -> System.out.println(b.name));
    }

    static void test1() {
        List<Foo> foos = new ArrayList<>();

        IntStream
            .range(1, 4)
            .forEach(num -> foos.add(new Foo("Foo" + num)));

        foos.forEach(f ->
            IntStream
                .range(1, 4)
                .forEach(num -> f.bars.add(new Bar("Bar" + num + " <- " + f.name))));

        foos.stream()
            .flatMap(f -> f.bars.stream())
            .forEach(b -> System.out.println(b.name));
    }

}