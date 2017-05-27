package com.winterbe.java8.samples.lambda.beans;

/**
 * @author edliao
 *         <p>
 *         interface with default method
 */
public interface Printer {
    //wait for implement
    void print(String str);

    //use print
    default void printWelcome() {
        print("welcome");
    }

    //default method
    default String getName() {
        return "Printer";
    }
}