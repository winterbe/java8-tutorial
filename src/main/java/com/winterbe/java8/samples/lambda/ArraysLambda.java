package com.winterbe.java8.samples.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * @author Benjamin Winterberg
 */
public class ArraysLambda {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");

        usualSort(names);
        lambda1Sort(names);
		lambda2Sort(names);
        lambda3Sort(names);
        System.out.println(names);

        names.sort(Collections.reverseOrder());
        System.out.println(names);

        List<String> names2 = Arrays.asList("peter", null, "anna", "mike", "xenia");
        names2.sort(Comparator.nullsLast(String::compareTo));
        System.out.println(names2);

        
        List<String> names3 = null;
        Optional.ofNullable(names3).ifPresent(list -> list.sort(Comparator.naturalOrder()));
        System.out.println(names3);
    }

	private static void lambda3Sort(List<String> names) {
		Collections.sort(names, (a, b) -> b.compareTo(a));
	}

	private static void lambda2Sort(List<String> names) {
		Collections.sort(names, (String a, String b) -> b.compareTo(a));
	}

	private static void lambda1Sort(List<String> names) {
		Collections.sort(names, (String a, String b) -> {
            return b.compareTo(a);
        });
	}

	private static void usualSort(List<String> names) {
		Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });
	}

}