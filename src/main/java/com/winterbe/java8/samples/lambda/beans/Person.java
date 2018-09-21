package com.winterbe.java8.samples.lambda.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    public static Person[] getPerson(){
        List<Person> result = new ArrayList<>();
        for(int i = 0 ;i<10;i++){
            result.add(new Person("P-"+i,i%3));
        }
        return result.toArray(new Person[0]);
    }
}