package com.winterbe.java8.samples.stream;

import com.winterbe.java8.samples.lambda.beans.Person;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author Benjamin Winterberg
 */
public class Streams10 {

    public static List<Person> persons = Arrays.asList(
            new Person("Max", 18),
            new Person("Peter", 23),
            new Person("Pamela", 23),
            new Person("David", 12));


    public void test1() {
        List<Person> filtered =
                persons
                        .stream()
                        .filter(p -> p.getName().startsWith("P"))
                        .collect(Collectors.toList());

        System.out.println(filtered);    // [Peter, Pamela]
    }

    public void test2() {
        Map<Integer, List<Person>> personsByAge = persons
                .stream()
                .collect(Collectors.groupingBy(p -> p.age));

        personsByAge
                .forEach((age, p) -> System.out.format("age %s: %s\n", age, p));

        // age 18: [Max]
        // age 23:[Peter, Pamela]
        // age 12:[David]
    }

    public void test3() {
        Double averageAge = persons
                .stream()
                .collect(Collectors.averagingInt(p -> p.age));

        System.out.println(averageAge);     // 19.0
    }

    public void test4() {
        IntSummaryStatistics ageSummary =
                persons
                        .stream()
                        .collect(Collectors.summarizingInt(p -> p.age));

        System.out.println(ageSummary);
        // IntSummaryStatistics{count=4, sum=76, min=12, average=19,000000, max=23}
    }

    public void test5() {
        String names = persons
                .stream()
                .filter(p -> p.age >= 18)
                .map(p -> p.name)
                .collect(Collectors.joining(" and ", "In Germany ", " are of legal age."));

        System.out.println(names);
        // In Germany Max and Peter and Pamela are of legal age.
    }

    public void test6() {
        Map<Integer, String> map = persons
                .stream()
                .collect(Collectors.toMap(
                        p -> p.age,
                        p -> p.name,
                        (name1, name2) -> name1 + ";" + name2));

        System.out.println(map);
        // {18=Max, 23=Peter;Pamela, 12=David}
    }

    @Test
    public void test7() {
        Collector<Person, StringJoiner, String> personNameCollector =
                Collector.of(
                        () -> new StringJoiner(" | "),          // supplier
                        (j, p) -> j.add(p.name.toUpperCase()),  // accumulator
                        (j1, j2) -> j1.merge(j2),               // combiner
                        StringJoiner::toString);                // finisher

        String names = persons
                .stream()
                .collect(personNameCollector);

        System.out.println(names);  // MAX | PETER | PAMELA | DAVID
    }

    public void test8() {
        Collector<Person, StringJoiner, String> personNameCollector =
                Collector.of(
                        () -> {
                            System.out.println("supplier");
                            return new StringJoiner(" | ");
                        },
                        (j, p) -> {
                            System.out.format("accumulator: p=%s; j=%s\n", p, j);
                            j.add(p.name.toUpperCase());
                        },
                        (j1, j2) -> {
                            System.out.println("merge");
                            return j1.merge(j2);
                        },
                        j -> {
                            System.out.println("finisher");
                            return j.toString();
                        });

        String names = persons
                .stream()
                .collect(personNameCollector);

        System.out.println(names);  // MAX | PETER | PAMELA | DAVID
    }

    public void test9() {
        Collector<Person, StringJoiner, String> personNameCollector =
                Collector.of(
                        () -> {
                            System.out.println("supplier");
                            return new StringJoiner(" | ");
                        },
                        (j, p) -> {
                            System.out.format("accumulator: p=%s; j=%s\n", p, j);
                            j.add(p.name.toUpperCase());
                        },
                        (j1, j2) -> {
                            System.out.println("merge");
                            return j1.merge(j2);
                        },
                        j -> {
                            System.out.println("finisher");
                            return j.toString();
                        });

        String names = persons
                .parallelStream()
                .collect(personNameCollector);

        System.out.println(names);  // MAX | PETER | PAMELA | DAVID
    }
}
