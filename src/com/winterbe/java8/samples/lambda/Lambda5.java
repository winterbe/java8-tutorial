package com.winterbe.java8.samples.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

/**
 * Created by grijesh
 */
public class Lambda5 {

    //Pre-Defined Functional Interfaces
    public static void main(String... args) {

        //BiConsumer Example
        BiConsumer<String, Integer> printKeyAndValue
                = (key, value) -> System.out.println(key + "-" + value);

        printKeyAndValue.accept("One", 1);
        printKeyAndValue.accept("Two", 2);

        System.out.println("##################");

        //Java Hash-Map foreach supports BiConsumer
        HashMap<String, Integer> dummyValues = new HashMap<>();
        dummyValues.put("One", 1);
        dummyValues.put("Two", 2);
        dummyValues.put("Three", 3);

        dummyValues.forEach((key, value) -> System.out.println(key + "-" + value));

        //BiFunction Example
        BiFunction<Integer, Integer, Integer> addition = (a, b) -> a + b;
        addition.apply(1, 2); //3

        //Unary Operator
        UnaryOperator<Integer> squareOfElements = (a) -> a * a;
        List<Integer> dummyList = Arrays.asList(1, 2, 3);

        dummyList
                .stream()
                .map(squareOfElements::apply)
                .forEach(System.out::println); // 1 4 9

        // Binary Operator
        BinaryOperator<String> appendKeyAndValue = (key, value) -> key + ":" + value;

        HashMap<String, String> dummyMap = new HashMap<>();
        dummyMap.put("ABC", "123");
        dummyMap.put("XYZ", "321");

        List<String> appendedKeyValuesPair = new ArrayList<>();

        dummyMap
                .forEach((key, value) -> appendedKeyValuesPair.add(appendKeyAndValue.apply(key, value)));

        appendedKeyValuesPair.forEach(System.out::println); //ABC:123 XYZ:321
    }
}
