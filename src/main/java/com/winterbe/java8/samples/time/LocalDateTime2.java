package com.winterbe.java8.samples.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by edliao on 2017/5/26.
 */
public class LocalDateTime2 {

    public static void main(String[] a) {
        /**
         * formatter by String
         */
        LocalDate date = LocalDate.now();
        print("default date", date);
        LocalTime time = LocalTime.now();
        print("default time", time);
        LocalDateTime dateTime = LocalDateTime.now();
        print("default dateTime", dateTime);

        //yyyy-MM-dd hh:mm:ss
        print("yyyy-MM-dd hh:mm:ss format dateTime", dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
        //yy_MM_dd
        print("DIY format dateTime", dateTime.format(DateTimeFormatter.ofPattern("yy_MM_dd")));

    }

    public static void print(Object title, Object content) {
        System.out.println(title + "|" + content);
    }
}
