package com.winterbe.java8.samples.time;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

/**
 * @author Benjamin Winterberg
 */
public class LocalTimeTest {

    public static void main(String[] args) {
        // 创建Localtime  现在的本地时间, 时区时间
        LocalTime now1 = LocalTime.now();
        LocalTime now2 = LocalTime.now(ZoneId.systemDefault());
        LocalTime now3 = LocalTime.now(Clock.systemDefaultZone());
        LocalTime late = LocalTime.of(23, 59, 59);

        System.out.println(now1);
        System.out.println(now2);
        System.out.println(now3);
        System.out.println(late);


        //格式化操作
       DateTimeFormatter germanFormatter =
                DateTimeFormatter
                        .ofPattern("HH:mm:ss");

        LocalTime leetTime = LocalTime.parse("23:37:30", germanFormatter);
        System.out.println(leetTime);

        //增减操作
        LocalTime localTimePlus = now1.plusHours(1L);

        //判断操作
        System.out.println(now1.isBefore(now2));  // false

        //间隔操作
        long hoursBetween = ChronoUnit.HOURS.between(now1, now2);
        long minutesBetween = ChronoUnit.MINUTES.between(now1, now2);
        System.out.println(hoursBetween);
        System.out.println(minutesBetween);


    }

}