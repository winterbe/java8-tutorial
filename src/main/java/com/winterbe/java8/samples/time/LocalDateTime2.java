package com.winterbe.java8.samples.time;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import org.junit.Test;

/**
 * Created by edliao on 2017/5/26.
 */
public class LocalDateTime2 {

  public static void main(String[] a) {
    //formatter by String
    LocalDate date = LocalDate.now();
    print("default date", date);
    LocalTime time = LocalTime.now();
    print("default time", time);
    LocalDateTime dateTime = LocalDateTime.now();
    print("default dateTime", dateTime);

    //yyyy-MM-dd hh:mm:ss
    print("yyyy-MM-dd hh:mm:ss format dateTime",
        dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
    //yy_MM_dd
    print("DIY format dateTime", dateTime.format(DateTimeFormatter.ofPattern("yy_MM_dd")));

  }

  public static void print(Object title, Object content) {
    System.out.println(title + "|" + content);
  }

  @Test
  public void testGetDate() {
    LocalDateTime toYear = LocalDateTime.of(2014, 1, 1, 0, 0);
    System.out.println(toYear);
    System.out.println(Timestamp.valueOf(toYear));
  }


}
