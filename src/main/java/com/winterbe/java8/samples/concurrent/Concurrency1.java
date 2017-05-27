package com.winterbe.java8.samples.concurrent;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/**
 * @author Benjamin Winterberg
 */
public class Concurrency1 {

    static int count;

    public static void main(String[] args) {
        Consumer<UUID> print = uuid -> System.out.println(String.valueOf(count++) + ":" + uuid);

        ConcurrentHashMap<Integer, UUID> concurrentHashMap = new ConcurrentHashMap<>();

        for (int i = 0; i < 100; i++) {
            concurrentHashMap.put(i, UUID.randomUUID());
        }

        int threshold = 1;

        concurrentHashMap.forEachValue(threshold, print);
        System.out.println("-------------------------------------------");

        concurrentHashMap.forEach((id, uuid) -> {
            if (id % 10 == 0) {
                System.out.println(String.format("%s: %s", id, uuid));
            }
        });
        System.out.println("-------------------------------------------");

        UUID searchResult = concurrentHashMap.search(threshold, (id, uuid) -> {
            if (String.valueOf(uuid).startsWith(String.valueOf(id))) {
                return uuid;
            }
            return null;
        });

        System.out.println(searchResult);
    }
}
