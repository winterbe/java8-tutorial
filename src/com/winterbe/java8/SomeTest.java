package com.winterbe.java8;

import java.util.Optional;

/**
 * @author Benjamin Winterberg
 */
public class SomeTest {

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

        Optional<String> optional = Some.of(something)
                .get(Outer::getNested)
                .get(Nested::getInner)
                .get(Inner::getFoo)
                .get();

        System.out.println(optional.isPresent());

        something.getNested().inner = null;

        optional = Some.of(() ->
                something.getNested().getInner().getFoo());

        System.out.println(optional.isPresent());


        String x = null;
        String y = "boo";
        String z = Some.of(x).orElse(y);

        System.out.println(z);


    }

}
