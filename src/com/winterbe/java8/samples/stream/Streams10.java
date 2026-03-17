package com.winterbe.java8.samples.stream;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Optimized version of Streams10.
 */
public class Streams10 {

    // ✅ Make fields final (immutability = safer + cleaner)
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

        filterByName(persons);
        groupByAge(persons);
        averageAge(persons);
        ageStatistics(persons);
        joiningNames(persons);
        mapByAge(persons);
        customCollector(persons);
        parallelCollector(persons);
    }

    // ✅ 1. Filter
    private static void filterByName(List<Person> persons) {
        List<Person> result = persons.stream()
            .filter(p -> p.name.startsWith("P"))
            .toList(); // Java 16+

        System.out.println(result);
    }

    // ✅ 2. Grouping
    private static void groupByAge(List<Person> persons) {
        Map<Integer, List<Person>> grouped =
            persons.stream()
                   .collect(Collectors.groupingBy(p -> p.age));

        grouped.forEach((age, p) ->
            System.out.printf("age %d: %s%n", age, p));
    }

    // ✅ 3. Average
    private static void averageAge(List<Person> persons) {
        double avg = persons.stream()
            .collect(Collectors.averagingInt(p -> p.age));

        System.out.println(avg);
    }

    // ✅ 4. Statistics
    private static void ageStatistics(List<Person> persons) {
        IntSummaryStatistics stats = persons.stream()
            .collect(Collectors.summarizingInt(p -> p.age));

        System.out.println(stats);
    }

    // ✅ 5. Joining (cleaner mapping)
    private static void joiningNames(List<Person> persons) {
        String result = persons.stream()
            .filter(p -> p.age >= 18)
            .map(p -> p.name)
            .collect(Collectors.joining(
                " and ",
                "In Germany ",
                " are of legal age."
            ));

        System.out.println(result);
    }

    // ✅ 6. toMap with merge function
    private static void mapByAge(List<Person> persons) {
        Map<Integer, String> map = persons.stream()
            .collect(Collectors.toMap(
                p -> p.age,
                p -> p.name,
                (n1, n2) -> n1 + ";" + n2
            ));

        System.out.println(map);
    }

    // ✅ 7. Custom collector (simplified)
    private static void customCollector(List<Person> persons) {
        String result = persons.stream()
            .map(p -> p.name.toUpperCase())
            .collect(Collectors.joining(" | "));

        System.out.println(result);
    }

    // ✅ 8. Parallel-safe collector
    private static void parallelCollector(List<Person> persons) {
        Collector<Person, ?, String> collector =
            Collectors.mapping(
                p -> p.name.toUpperCase(),
                Collectors.joining(" | ")
            );

        String result = persons.parallelStream()
            .collect(collector);

        System.out.println(result);
    }
}