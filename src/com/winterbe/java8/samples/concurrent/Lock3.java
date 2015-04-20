package com.winterbe.java8.samples.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Benjamin Winterberg
 */
public class Lock3 {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Map<String, String> map = new HashMap<>();

        ReadWriteLock lock = new ReentrantReadWriteLock();

        executor.submit(() -> {
            lock.writeLock().lock();
            try {
                TimeUnit.SECONDS.sleep(1);
                map.put("foo", "bar");
            }
            catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            finally {
                lock.writeLock().unlock();
            }
        });

        executor.submit(() -> {
            lock.readLock().lock();
            try {
                String foo = map.get("foo");
                System.out.println(foo);
                TimeUnit.SECONDS.sleep(1);
            }
            catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            finally {
                lock.readLock().unlock();
            }
        });

        executor.submit(() -> {
            lock.readLock().lock();
            try {
                String foo = map.get("foo");
                System.out.println(foo);
                TimeUnit.SECONDS.sleep(1);
            }
            catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            finally {
                lock.readLock().unlock();
            }
        });

        ConcurrentUtils.stop(executor);
    }

}
