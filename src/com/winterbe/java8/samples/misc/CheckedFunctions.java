package com.winterbe.java8.samples.misc;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Utilities for hassle-free usage of lambda expressions who throw checked exceptions.
 *
 * @author Benjamin Winterberg
 */
public final class CheckedFunctions {

    @FunctionalInterface
    public interface CheckedConsumer<T> {
        void accept(T input) throws Exception;
    }

    @FunctionalInterface
    public interface CheckedPredicate<T> {
        boolean test(T input) throws Exception;
    }

    @FunctionalInterface
    public interface CheckedFunction<F, T> {
        T apply(F input) throws Exception;
    }

    /**
     * Return a function which rethrows possible checked exceptions as runtime exception.
     *
     * @param function
     * @param <F>
     * @param <T>
     * @return
     */
    public static <F, T> Function<F, T> function(CheckedFunction<F, T> function) {
        return input -> {
            try {
                return function.apply(input);
            }
            catch (Exception e) {
                if (e instanceof RuntimeException) {
                    throw (RuntimeException) e;
                }
                throw new RuntimeException(e);
            }
        };
    }

    /**
     * Return a predicate which rethrows possible checked exceptions as runtime exception.
     *
     * @param predicate
     * @param <T>
     * @return
     */
    public static <T> Predicate<T> predicate(CheckedPredicate<T> predicate) {
        return input -> {
            try {
                return predicate.test(input);
            }
            catch (Exception e) {
                if (e instanceof RuntimeException) {
                    throw (RuntimeException) e;
                }
                throw new RuntimeException(e);
            }
        };
    }

    /**
     * Return a consumer which rethrows possible checked exceptions as runtime exception.
     *
     * @param consumer
     * @param <T>
     * @return
     */
    public static <T> Consumer<T> consumer(CheckedConsumer<T> consumer) {
        return input -> {
            try {
                consumer.accept(input);
            }
            catch (Exception e) {
                if (e instanceof RuntimeException) {
                    throw (RuntimeException) e;
                }
                throw new RuntimeException(e);
            }
        };
    }
}
