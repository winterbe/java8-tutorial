package com.winterbe.java8.samples.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Benjamin Winterberg
 */
public class Executors1 {

    public static void main(String[] args) {
        test1(3);
//        test1(7);
    }

    private static void test1(long seconds) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(seconds);
                System.out.println("task finished: " + Thread.currentThread().getName());
            }
            catch (InterruptedException e) {
                System.err.println("task interrupted");
            }
        });
        shutdown(executor);
    }

    static void shutdown(ExecutorService executor) {
        try {
            System.out.println("attempt to shutdown executor");
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);
        }
        catch (InterruptedException e) {
            System.err.println("termination interrupted");
        }
        finally {
            executor.shutdownNow();
            System.out.println("shutdown finished");
        }
    }
}
