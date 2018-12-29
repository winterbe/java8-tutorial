package com.winterbe.java8.samples.time;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static java.time.DayOfWeek.SUNDAY;
import static java.time.temporal.TemporalAdjusters.*;
import static java.time.temporal.TemporalAdjusters.dayOfWeekInMonth;

/**
 * @Author: Liushishuang
 * @Description:
 * @Date: 16:05 2018-12-28
 */
public class TemporalAdjusterTest {

    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        //本月/本年/下个月/明年  第一天
        LocalDateTime firstDayOfMonth = now.with(firstDayOfMonth());
        LocalDateTime firstDayOfYear = now.with(firstDayOfYear());
        LocalDateTime firstDayOfNextMonth = now.with(firstDayOfNextMonth());
        LocalDateTime firstDayOfNextYear = now.with(firstDayOfNextYear());

        //星期操作
        LocalDateTime dayOfWeekLocalDateTime = now.with(firstInMonth(SUNDAY));// 同一个月中,第一个符合星期几要求的值的日期对象
        LocalDateTime nextSunday = now.with(next(SUNDAY)); //下个周一
        now.with(nextOrSame(SUNDAY));//如果该日期符合要求,直接返回该对象
        now.with(dayOfWeekInMonth(1, SUNDAY)); // 本月的第一个周日 (0上个月的最后一个, -1本月最后一个...可以进行延伸到上月/下月)
    }
}
