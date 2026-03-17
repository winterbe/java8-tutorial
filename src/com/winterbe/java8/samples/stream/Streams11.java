package com.winterbe.java8.samples.stream;

import java.util.List;

/**
 * Optimized version of Streams11.
 */
public class Streams11 {

    // ✅ Immutable model (better for parallel streams)
    static class Person {
        final String name;
        final int age;

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
        List<Person> persons = List.of(
            new Person("Max", 18),
            new Person("Peter", 23),
            new Person("Pamela", 23),
            new Person("David", 12)
        );

        findOldest(persons);
        sumAges(persons);
        sumAgesParallel(persons);
    }

    // ✅ 1. Find oldest person (cleaner)
    private static void findOldest(List<Person> persons) {
        persons.stream()
            .max((p1, p2) -> Integer.compare(p1.age, p2.age))
            .ifPresent(System.out::println);
    }

    // ❌ Old reduce misuse replaced with proper sum
    // ✅ 2. Sum ages (best practice)
    private static void sumAges(List<Person> persons) {
        int sum = persons.stream()
            .mapToInt(p -> p.age)
            .sum();

        System.out.println("Sum (sequential): " + sum);
    }

    // ✅ 3. Parallel-safe version
    private static void sumAgesParallel(List<Person> persons) {
        int sum = persons.parallelStream()
            .mapToInt(p -> p.age)
            .sum();

        System.out.println("Sum (parallel): " + sum);
    }

    // ⚠️ Educational: reduce with debug (safe version)
    private static void debugReduce(List<Person> persons) {
        int sum = persons.parallelStream()
            .reduce(0,
                (partialSum, p) -> {
                    System.out.printf(
                        "accumulator: sum=%d; person=%s; thread=%s%n",
                        partialSum, p, Thread.currentThread().getName()
                    );
                    return partialSum + p.age;
                },
                (sum1, sum2) -> {
                    System.out.printf(
                        "combiner: sum1=%d; sum2=%d; thread=%s%n",
                        sum1, sum2, Thread.currentThread().getName()
                    );
                    return sum1 + sum2;
                });

        System.out.println("Final sum: " + sum);
    }
}