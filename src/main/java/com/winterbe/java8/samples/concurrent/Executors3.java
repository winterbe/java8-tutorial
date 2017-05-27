package com.winterbe.java8.samples.concurrent;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author Benjamin Winterberg
 */
@Slf4j
public class Executors3 {

@Test
    public void test5() throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newWorkStealingPool();

        List<Callable<String>> callables = Arrays.asList(
                callable("task1", 2),
                callable("task2", 1),
                callable("task3", 3));

        String result = executor.invokeAny(callables);
        log.debug(result);

        executor.shutdown();
    }

    public Callable<String> callable(String result, long sleepSeconds) {
        return () -> {
            TimeUnit.SECONDS.sleep(sleepSeconds);
            return result;
        };
    }

    @Test
    public void test4() throws InterruptedException {
        ExecutorService executor = Executors.newWorkStealingPool();

        List<Callable<String>> callables = Arrays.asList(
                () -> "task1",
                () -> {
                    Thread.sleep(1000);
                    return "task2";
                },
                () -> "task3");

        log.debug("run");
        executor.invokeAll(callables)
                .stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (Exception e) {
                        throw new IllegalStateException(e);
                    }
                })
                .forEach(log::debug);

        executor.shutdown();
        log.debug("shutdown");
    }

    @Test
    public void test33() throws ExecutionException, InterruptedException {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        Runnable task = () -> {
            try {
                log.debug("Scheduling start: " + System.nanoTime());
                TimeUnit.SECONDS.sleep(2);
                log.debug("Scheduling end: " + System.nanoTime());
            } catch (InterruptedException e) {
                System.err.println("task interrupted");
            }
        };

        log.debug("TaskRun: " + System.nanoTime());
        executor.scheduleAtFixedRate(task, 0, 3, TimeUnit.SECONDS).get();
    }

    @Test
    public void test3() throws ExecutionException, InterruptedException {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        Runnable task = () -> {
            try {
                log.debug("Scheduling start: " + System.nanoTime());
                TimeUnit.SECONDS.sleep(2);
                log.debug("Scheduling end: " + System.nanoTime());
            } catch (InterruptedException e) {
                System.err.println("task interrupted");
            }
        };

        log.debug("TaskRun: " + System.nanoTime());
        executor.scheduleWithFixedDelay(task, 0, 2, TimeUnit.SECONDS).get();
    }

    @Test
    public void test2() throws ExecutionException, InterruptedException {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        Runnable task = () -> log.debug("Scheduling: " + System.nanoTime());
        int initialDelay = 3;
        int period = 2;

        log.debug("start");
        executor.scheduleAtFixedRate(task, initialDelay, period, TimeUnit.SECONDS).get();
        log.debug("end");
    }

    @Test
    public void test1() throws InterruptedException, ExecutionException {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        Runnable task = () -> log.debug("Scheduling: " + System.nanoTime());
        int delay = 3;
        ScheduledFuture<?> future = executor.schedule(task, delay, TimeUnit.SECONDS);

        log.debug("Executor is running and main-thread sleep");
        TimeUnit.MILLISECONDS.sleep(1337);

        long remainingDelay = future.getDelay(TimeUnit.MILLISECONDS);

        log.debug("Wait for get");
        future.get();

        log.debug("Remaining Delay: {}ms", remainingDelay);
    }

}
