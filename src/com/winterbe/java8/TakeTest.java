package com.winterbe.java8;

import java.util.Optional;

/**
 * @author Benjamin Winterberg
 */
public class TakeTest {

    static class Outer {
        Nested nested = new Nested();

        Nested getNested() {
            return nested;
        }
    }

    static class Nested {
        Inner inner = new Inner();

        Inner getInner() {
            return inner;
        }
    }

    static class Inner {
        String foo = "foo";

        String getFoo() {
            return foo;
        }
    }

    public static void main(String[] args) {
        Outer something = new Outer();

        Optional<String> optional = Take.of(something)
                .take(Outer::getNested)
                .take(Nested::getInner)
                .take(Inner::getFoo)
                .get();

        System.out.println(optional.get());
    }

}
