package com.winterbe.java8.samples.time;

import java.time.ZoneId;

/**
 * @Author: Liushishuang
 * @Description:
 * @Date: 11:36 2018-12-28
 */
public class ZoneIdTest {
    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        System.out.println("系统所有可见时区ID:"+ZoneId.getAvailableZoneIds());

        ZoneId zoneId = ZoneId.systemDefault();
        System.out.println("系统默认时区:"+zoneId); //Asia/Shanghai
        System.out.println(zoneId.getRules()); //ZoneRules[currentStandardOffset=+08:00]
    }
}
