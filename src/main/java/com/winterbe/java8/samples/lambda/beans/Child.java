package com.winterbe.java8.samples.lambda.beans;

/**
 * Created by edliao on 2017/5/27.
 */
public class Child extends Parent {
    @Override
    public void print(String str) {
        System.out.println("class implement : {" + str + "}");
    }

    @Override
    public String getName() {
        return "Child";
    }
}
