package com.winterbe.java8.samples.lambda.beans;

/**
 * Created by edliao on 2017/5/27.
 */
public abstract class Parent {
    public abstract void print(String str);

    public void printWelcome() {
        print("welcome");
    }

    public String getName() {
        return "Parent";
    }
}
