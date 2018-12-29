package com.winterbe.java8.samples.time;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

/**
 * @author Benjamin Winterberg
 * 线程安全
 * //java.time下一些类的时间值格式
 * LocalDate
 * LocalTime
 * LocalDateTime
 * OffsetTime
 * OffsetDateTime
 * ZonedDateTime --- 在jdbc中没有对应的类型
 * Year
 * YearMonth 2012-123
 * MonthDay  12-03
 * Instant
 * Duration,Period  日期之间的时间量
 * //工具类
 * DateTimeFormatter ----- 将日期类型转换成字符串类型
 * ChronoUnit ----- 计算出两点之间的时间量
 * TemporalAdjuster ----- 例如date.with(TemporalAdjuster.firstDayOfMonth())
 */
public class LocalDateTest {

    public static void main(String[] args) {
        //创建LocalDate
        LocalDate today = LocalDate.now();
        LocalDate now = LocalDate.now(ZoneId.systemDefault());
        LocalDate now1 = LocalDate.now(Clock.systemDefaultZone());
        LocalDate independenceDay = LocalDate.of(2014, Month.JULY, 4);
        //格式化操作
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate parseLocalDate = LocalDate.parse("2018-08-08", dateTimeFormatter);
        String formatLocalDate = dateTimeFormatter.format(parseLocalDate);

        //增减操作
        LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
        LocalDate yesterday = tomorrow.minusDays(2);

        //获取星期 FRIDAY
        DayOfWeek dayOfWeek = independenceDay.getDayOfWeek();

        //判断操作
        boolean beforeResult = now.isBefore(now1);
        boolean leapYear = now.isLeapYear();

        //间隔操作(时间差)
        Duration duration = Duration.between(now, now1);
        long toDays = duration.toDays();

        long yearBetween = ChronoUnit.YEARS.between(now, now1);



    }

}