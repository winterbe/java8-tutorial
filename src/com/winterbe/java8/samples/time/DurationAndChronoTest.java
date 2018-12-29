package com.winterbe.java8.samples.time;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;

/**
 * @Author: Liushishuang
 * @Description:
 * @Date: 15:16 2018-12-28
 */
public class DurationAndChronoTest {
    /**
     * | **日期-时间类 时间间隔** | 是否是静态方法 | 方法描述 |
     * | :---: | :---:| :---: |
     * | between | 是 | 创建两个时间点之间的interval |
     * | from | 是 | 由一个临时节点创建interval |
     * | of | 是 | 由它的组成部分创建interval的实例 |
     * | parse | 是 | 字符串创建 |
     * | addTo | 否 | 创建该interval的副本,并将其叠加到某个指定的temporal对象 |
     * | get | 否 | 读取状态 |
     * | isNegative | 否 | 检查是否为负值 |
     * | isZero | 否 | 检查时长是否为零 |
     * | minus/plus | 否 | 增减 |
     * | multipliedBy | 否 | 值乘以某个标量创建副本 |
     * | negated | 否 | 以忽略某个时长的方式创建副本 |
     * | subtrctFrom | 否 | 从某个指定的temporal对象减去interval |
     *
     * @param args
     */
    public static void main(String[] args) {
        //Duration
        final LocalDateTime from = LocalDateTime.of(2014, Month.APRIL, 16, 0, 0, 0);//年月日时分秒
        final LocalDateTime to = LocalDateTime.of(2015, Month.APRIL, 16, 23, 59, 59);
        final Duration duration = Duration.between(from, to);
        System.out.println("Duration in days: " + duration.toDays());


        //ChronoUnit
        long hoursBetween = ChronoUnit.HOURS.between(from, to);
        System.out.println("hoursBetween = " + hoursBetween);
    }
}
