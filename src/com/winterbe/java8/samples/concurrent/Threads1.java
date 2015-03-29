package com.winterbe.java8.samples.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * @author Benjamin Winterberg
 */
public class Threads1 {

    public static void main(String[] args) {
        test1();
//        test2();
//        test3();
    }

    private static void test3() {
        Runnable runnable = () -> {
            try {
                System.out.println("Foo " + Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(1);
                System.out.println("Bar " + Thread.currentThread().getName());
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

    private static void test2() {
        Runnable runnable = () -> {
            try {
                System.out.println("Foo " + Thread.currentThread().getName());
                Thread.sleep(1000);
                System.out.println("Bar " + Thread.currentThread().getName());
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

    private static void test1() {
        Runnable runnable = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
        };

        runnable.run();

        Thread thread = new Thread(runnable);
        thread.start();

        System.out.println("Done!");
    }
}
