package com.winterbe.java8.samples.stream;

import com.winterbe.java8.samples.lambda.beans.Person;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author Benjamin Winterberg
 */
public class Streams8 {

    public static void main(String[] args) {
        Arrays.asList("a1", "a2", "a3")
                .stream()
                .findFirst()
                .ifPresent(System.out::println);

        Stream.of("a1", "a2", "a3")
                .map(s -> s.substring(1))
                .mapToInt(Integer::parseInt)
                .max()
                .ifPresent(System.out::println);

        IntStream.range(1, 4)
                .mapToObj(i -> "a" + i)
                .forEach(System.out::println);

        Arrays.stream(new int[]{1, 2, 3})
                .map(n -> 2 * n + 1)
                .average()
                .ifPresent(System.out::println);

        Stream.of(1.0, 2.0, 3.0)
                .mapToInt(Double::intValue)
                .mapToObj(i -> "a" + i)
                .forEach(System.out::println);

    }


    @Test
    public void testGrouping() {
//        System.out.println(
//                Arrays.stream(Person.getPerson()).collect(
//                        Collectors.groupingBy(Person::getAge)));

        System.out.println(
                Arrays.stream(Person.getPerson()).collect(
                        Collectors.groupingBy(row -> row.getAge(),
                                Collectors.mapping(row -> row.getName(), Collectors.toList()))));


    }

    @Test
    public void testEmptySteam() {
        Arrays.stream(new String[0]).forEach(s -> System.out.println(s));
    }
}
