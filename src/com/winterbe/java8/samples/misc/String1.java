package com.winterbe.java8.samples.misc;

import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * @author Benjamin Winterberg
 */
public class String1 {

    public static void main(String[] args) {
        testJoin();
        testChars();
        testPatternPredicate();
        testPatternSplit();
    }

    private static void testChars() {
        String string = "foobar";
        string.chars()
                .filter(c -> c > 100)
                .mapToObj(c -> (char)c)
                .forEach(System.out::println);
    }

    private static void testPatternSplit() {
        Pattern.compile(":")
                .splitAsStream("foobar:foo:bar")
                .sorted()
                .forEach(System.out::println);
    }

    private static void testPatternPredicate() {
        Pattern pattern = Pattern.compile(".*123.*");
        Predicate<String> predicate = pattern.asPredicate();
        System.out.println(predicate.test("a123b"));
        System.out.println(predicate.test("boom"));
    }

    private static void testJoin() {
        String string = String.join(";", "a", "b", "c", "d");
        System.out.println(string);
    }
}
