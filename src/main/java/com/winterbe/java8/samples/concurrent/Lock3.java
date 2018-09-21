package com.winterbe.java8.samples.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Benjamin Winterberg
 */
@Slf4j
public class Lock3 {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Map<String, String> map = new HashMap<>();

        ReadWriteLock lock = new ReentrantReadWriteLock();

        executor.submit(() -> {
            log.debug("start write");
            lock.writeLock().lock();
            try {
                log.debug("writing");
                ConcurrentUtils.sleep(1);
                map.put("foo", "bar");
            } finally {
                lock.writeLock().unlock();
                log.debug("END write");
            }
        });

        Runnable readTask = () -> {
            log.debug("start read");
            lock.readLock().lock();
            try {
                log.debug("reading");
                log.debug(map.get("foo"));
                ConcurrentUtils.sleep(1);
            } finally {
                lock.readLock().unlock();
                log.debug("END read");
            }
        };
        executor.submit(readTask);
        executor.submit(readTask);

        ConcurrentUtils.stop(executor);
    }

}
