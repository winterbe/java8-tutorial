package com.winterbe.java8.samples.misc;

/**
 * @author Benjamin Winterberg
 */
public class StrictMath1 {

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE + 1);

        try {
            Math.addExact(Integer.MAX_VALUE, 1);
        }
        catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }
    }
}
