package com.winterbe.java8.samples.lambda;

import java.util.Date;
import java.util.HashMap;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import org.junit.Test;

/**
 * Created by grijesh
 */
public class Lambda5 {

  //Pre-Defined Functional Interfaces
  public static void main(String... args) {

    //BiConsumer Example
    BiConsumer<String, Integer> printKeyAndValue = (key, value) -> System.out
        .println(key + "-" + value);

    printKeyAndValue.accept("One", 1);
    printKeyAndValue.accept("Two", 2);

    System.out.println("----------------------------");

    //Java Hash-Map foreach supports BiConsumer
    HashMap<String, Integer> dummyValues = new HashMap<>();
    dummyValues.put("One", 1);
    dummyValues.put("Two", 2);
    dummyValues.put("Three", 3);

    dummyValues.forEach((key, value) -> System.out.println(key + "-" + value));

    System.out.println("----------------------------");

    // same as
    dummyValues.forEach(printKeyAndValue);

  }

  @Test
  public void testBIConsumer() {
    String s = "ChengDuCity";
    Date d = new Date();
    printDate().accept(s, d);
    printDate2(s, d);
  }


  public BiConsumer<String, Date> printDate() {
    return (s, date) -> {
      System.out.println("Welcome to " + s);
      System.out.println("Today is " + date);
    };
  }

  public void printDate2(String s, Date d) {
    printDate().accept(s, d);
  }

  @Test
  public void useFunctionAsParameter() {
    Consumer<String> stringConsumer = s -> System.out
        .println(String.format("This is a String ,length[%s],content[%s]", s.length(), s));
    Consumer<Long> longConsumer = s -> System.out
        .println(String.format("This is a Long ,length[%s],content[%s]", s.toString().length(), s));

    dealData("i'm String",stringConsumer,longConsumer);
    dealData(1241L,stringConsumer,longConsumer);
  }


  public void dealData(Object data, Consumer<String> stringConsumer, Consumer<Long> longConsumer) {
    if (data instanceof String) {
      stringConsumer.accept((String) data);
    } else if (data instanceof Long) {
      longConsumer.accept((Long) data);
    } else {
      System.out.println("No available adapters.");
    }
  }


}
