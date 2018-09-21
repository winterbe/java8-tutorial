package com.winterbe.java8.samples.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Benjamin Winterberg
 */
@Slf4j
public class Lock2 {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        ReentrantLock lock = new ReentrantLock();

        executor.submit(() -> {
            lock.lock();
            try {
                ConcurrentUtils.sleep(5);
            } finally {
                lock.unlock();
            }
        });

        executor.submit(() -> {
            log.debug("Locked: " + lock.isLocked());
            log.debug("Held by me: " + lock.isHeldByCurrentThread());
            boolean locked = lock.tryLock();
            log.debug("Lock acquired: " + locked);
            if (!locked) {
                log.debug("Locked: " + lock.isLocked());
                lock.lock();
            }
            log.debug("Get Lock: " + lock.isHeldByCurrentThread());
        });

        ConcurrentUtils.stop(executor);
    }

}
