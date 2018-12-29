package com.winterbe.java8.samples.time;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

/**
 * @Author: Liushishuang
 * @Description:
 * @Date: 17:32 2018-12-27
 * Clock使用时区来访问当前的instant, date和time。 clock包括了zoneId
 * Clock类的millis()和systemDefaultZone()可以替换 System.currentTimeMillis()  TimeZone.getDefault()。
 */
public class ClockTest {
    public static void main(String[] args) {
        //获取时钟
        Clock clock = Clock.systemDefaultZone();
        Clock clock1 = Clock.systemUTC();//获取系统时钟，并将其转换成使用UTC时区的日期和时间
        Clock clock2 = Clock.system(ZoneId.of("Asia/Shanghai"));
        Clock clock3 = Clock.fixed(Instant.now(), ZoneId.systemDefault()); //固定瞬时时间时钟


        System.out.println("系统的时区:"+clock.getZone());
        System.out.println("系统时间日期(instant永远是格林尼治):" + clock.instant());
        System.out.println("时间毫秒:" + clock.millis());

        //将instant转换为Date
        Date date = Date.from(clock.instant());
        System.out.println(date);
    }
}
