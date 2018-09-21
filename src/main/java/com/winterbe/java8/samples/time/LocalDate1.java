package com.winterbe.java8.samples.time;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

/**
 * @author Benjamin Winterberg
 */
public class LocalDate1 {

    public static void main(String[] args) {
        /**
         * Only year month day
         */
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
        LocalDate yesterday = tomorrow.minusDays(2);

        System.out.println(today);
        System.out.println(tomorrow);
        System.out.println(yesterday);

        LocalDate independenceDay = LocalDate.of(2014, Month.JULY, 4);
        DayOfWeek dayOfWeek = independenceDay.getDayOfWeek();
        System.out.println(dayOfWeek);    // FRIDAY

        DateTimeFormatter germanFormatter =
                DateTimeFormatter
                        .ofLocalizedDate(FormatStyle.MEDIUM)
                        .withLocale(Locale.GERMAN);

        LocalDate xmas = LocalDate.parse("24.12.2014", germanFormatter);
        System.out.println(xmas);   // 2014-12-24


        /**
         * Period
         */
        LocalDate fiveDaysLater = LocalDate.now().plusDays(5);
        Period period = LocalDate.now().until(fiveDaysLater).plusMonths(2);

        System.out.println(period);
        System.out.println(period.isNegative());
        System.out.println(period.get(ChronoUnit.DAYS));

    }

}