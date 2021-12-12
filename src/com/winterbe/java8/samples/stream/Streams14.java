package com.winterbe.java8.samples.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Shruti Singh
 */

public class Streams14 {
	public static void main(String[] args) {

		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
		List<Integer> flatmappedList = list.stream().flatMap(value -> Stream.of(-1 * value, value))
				.collect(Collectors.toList());
		System.out.println(flatmappedList);

		// [-1, 1, -2, 2, -3, 3, -4, 4, -5, 5]
	}
}
