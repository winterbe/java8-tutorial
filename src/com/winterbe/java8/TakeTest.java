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

        System.out.println(optional.isPresent());

        something.getNested().inner = null;
        Optional<String> optional2 = Take.of(() ->
                something.getNested().getInner().getFoo());
        System.out.println(optional2.isPresent());


        String x = null;
        String y = "boo";
        String z = Take.of(x).orElse(y);

        System.out.println(z);


    }

}
