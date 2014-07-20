package com.winterbe.java8;

import java.util.Optional;

/**
 * @author Benjamin Winterberg
 */
public class Optional2 {

    static class Outer {
        Nested nested;
    }

    static class Nested {
        Inner inner;
    }

    static class Inner {
        String foo;
    }

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        Optional.of(new Outer())
            .flatMap(o -> Optional.ofNullable(o.nested))
            .flatMap(n -> Optional.ofNullable(n.inner))
            .flatMap(i -> Optional.ofNullable(i.foo))
            .ifPresent(System.out::println);
    }
}
