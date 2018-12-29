package com.winterbe.java8.samples.time;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Date;

import static java.time.DayOfWeek.SUNDAY;
import static java.time.temporal.TemporalAdjusters.*;


/**
 * @author Benjamin Winterberg
 */
public class LocalDateTimeTest {
    /**
     * | **日期-时间类 时间点** | 是否静态 | 描述 |
     * | :---: | :---:| :---: |
     * | from | 是 | 根据传入的temporal对象创建实例 |
     * | now | 是 | 根据系统时钟 |
     * | of | 是 | 根据组成部分 |
     * | parse | 是 | 根据字符串 |
     * | atOffset | 否 | 将Temporal对象和某个时区偏移相结合 |
     * | atZone | 否 | 和时区结合 |
     * | format | 否 | 使用指定的格式将对象转化为字符串(Instant不支持) |
     * | get | 否 | 读取一部分 |
     * | plus/minus | 否 | 增/减创建对象的副本 |
     * | with | 否 | 以Temporal对象为模板,对某些状态进行修改创建对
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        //构建日期时间,瞬时时间(时区)
        LocalDateTime sylvester = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59);
        LocalDateTime now = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime.of(localDate, localTime);


        DayOfWeek dayOfWeek = sylvester.getDayOfWeek();
        Month month = sylvester.getMonth();
        long minuteOfDay = sylvester.getLong(ChronoField.MINUTE_OF_DAY);


        //将LocalDateTime + 时区 => Instant对象使用
        Instant instant = sylvester
                .atZone(ZoneId.systemDefault())
                .toInstant();
        //将Instant => Date对象
        Date legacyDate = Date.from(instant);
        System.out.println(legacyDate);     // Wed Dec 31 23:59:59 CET 2014

        //格式化操作
        DateTimeFormatter formatter =
                DateTimeFormatter
                        .ofPattern("MMM dd, yyyy - HH:mm");

        LocalDateTime parsed = LocalDateTime.parse("Nov 03, 2014 - 07:13", formatter);
        String string = parsed.format(formatter);
        System.out.println(string);     // Nov 03, 2014 - 07:13


        //ZonedDateTime
        Clock clock = Clock.systemDefaultZone();// 获取系统默认时区 (当前瞬时时间 )
        final ZonedDateTime zonedDatetime = ZonedDateTime.now();
        final ZonedDateTime zonedDatetimeFromClock = ZonedDateTime.now(clock);
        final ZonedDateTime zonedDatetimeFromZone = ZonedDateTime.now(ZoneId.of("America/Los_Angeles"));



    }

}