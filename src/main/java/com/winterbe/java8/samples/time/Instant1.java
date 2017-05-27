package com.winterbe.java8.samples.time;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * @author edliao
 *         <p>
 *         Instant is a timestamp ,support operator like + - * /
 */
public class Instant1 {

    public static void main(String[] a) {
        Instant source = Instant.now();

        System.out.println(source);
        System.out.println(source.plus(5, ChronoUnit.SECONDS));
        System.out.println(source.minusMillis(50));
        System.out.println(source.isBefore(Instant.now()));
        System.out.println(source.toEpochMilli());

        System.out.println("-----------------------------------");
        /**
         * Duration is difference between 2 Instant
         */
        Instant begin = Instant.now();
        Instant end = Instant.now();
        Duration elapsed = Duration.between(begin, end);

        System.out.println(begin);
        System.out.println(end);
        System.out.println(elapsed);

        System.out.println(elapsed.toMillis());
        System.out.println(elapsed.dividedBy(10).minus(Duration.ofMillis(10)).isNegative());
        System.out.println(elapsed.isZero());
        System.out.println(elapsed.plusHours(3));
    }

}
