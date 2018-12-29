package com.winterbe.java8.samples.time;

import java.time.Clock;
import java.time.Instant;

/**
 * @Author: Liushishuang
 * @Description:
 * @Date: 15:15 2018-12-28
 */
public class InstantTest {
    public static void main(String[] args) {
        //获取瞬时的时间,从时钟获取瞬时时间
        Instant now = Instant.now();
        Instant now1 = Instant.now(Clock.systemDefaultZone());

        //秒和毫秒
        long epochSecond = now.getEpochSecond();
        long epochMilli = now.toEpochMilli();

    }
}
