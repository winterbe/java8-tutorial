package com.winterbe.java8.samples.misc;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Benjamin Winterberg
 */
public class Concurrency1 {

    public static void main(String[] args) {
        ConcurrentHashMap<Integer, UUID> concurrentHashMap = new ConcurrentHashMap<>();

        for (int i = 0; i < 100; i++) {
            concurrentHashMap.put(i, UUID.randomUUID());
        }

        int threshold = 1;

        concurrentHashMap.forEachValue(threshold, System.out::println);

        concurrentHashMap.forEach((id, uuid) -> {
            if (id % 10 == 0) {
                System.out.println(String.format("%s: %s", id, uuid));
            }
        });

        UUID searchResult = concurrentHashMap.search(threshold, (id, uuid) -> {
            if (String.valueOf(uuid).startsWith(String.valueOf(id))) {
                return uuid;
            }
            return null;
        });

        System.out.println(searchResult);
    }
}
