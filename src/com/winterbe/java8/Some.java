package com.winterbe.java8;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Simplified elvis-like operator. You can achieve the same with Optional but Take has simpler syntax.
 * 
 * @see Optional2
 * @author Benjamin Winterberg
 */
public class Some<T> {

    private static final Some<?> EMPTY = new Some<>(null);

    private T value;

    private Some(T value) {
        this.value = value;
    }

    public static <T> Optional<T> of(Supplier<T> resolver) {
        try {
            T result = resolver.get();
            return Optional.ofNullable(result);
        }
        catch (NullPointerException e) {
            return Optional.empty();
        }
    }

    public static <T> Some<T> of(T something) {
        return new Some<>(something);
    }

    public <S> Some<S> get(Function<? super T, S> resolver) {
        if (value == null) {
            return empty();
        }
        S result = resolver.apply(value);
        return new Some<>(result);
    }

    public Optional<T> get() {
        return Optional.ofNullable(value);
    }

    public T orElse(T fallback) {
        if (value != null) {
            return value;
        }
        return fallback;
    }

    @SuppressWarnings("unchecked")
    private static <T> Some<T> empty() {
        return (Some<T>) EMPTY;
    }

}
