package com.winterbe.java8;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Benjamin Winterberg
 */
public class Streams10 {

    static class Person {
        String name;
        int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        List<Person> persons =
            Arrays.asList(
                new Person("Max", 18),
                new Person("Peter", 23),
                new Person("Brenda", 23),
                new Person("David", 12));

        Map<Integer, List<Person>> personsByAge = persons
            .stream()
            .collect(Collectors.groupingBy(p -> p.age));

        personsByAge
            .forEach((age, p) -> System.out.format("age: %s; persons: %s\n", age, p));
    }

}
