package com.winterbe.java8.samples.lambda.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Benjamin Winterberg
 */
@Data
@NoArgsConstructor
public class Person {
    public String firstName;
    public String lastName;
    public String name;
    public int age;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}