package com.harvest.core.service.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Component
public class RedisIdMaker {

//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;

    private final static String DATE_TIME_PATTERN = "yyyy:MM:dd:HH:mm";

    /**
     * 时间戳开始时间，从2022年1月1号0点0时0分开始
     */
    private static final Long START_TIME = 1640995200L;

    /**
     * 订单生成数量  每天最多2的31次方个订单数量
     */
    private static final int COUNT_BITS = 32;

    private static final String ORDER_COUNT_KEY = "order:";


//    /**
//     * 根据redis生成唯一订单号
//     *
//     * @return
//     */
//    public Long generateNextId() {
//        // 获取当前时间
//        LocalDateTime now = LocalDateTime.now();
//        long currentStamp = now.toEpochSecond(ZoneOffset.UTC);
//        // 获取当前时间戳（秒）
//        long timeStamp = currentStamp - START_TIME;
//        // 组装成key=order:2022:01:01(组装成这种形式方便日后根据日期统计当天的订单数量)
//        String date = now.format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));
//        String redisKey = ORDER_COUNT_KEY + date;
//        // 订单自增长
//        long orderCount = stringRedisTemplate.opsForValue().increment(redisKey);
//        // 返回唯一订单号（拼接而来的）
//        return timeStamp << COUNT_BITS | orderCount;
//    }
}