package com.harvest.core.service.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author: Alodi
 * @Date: 2023/2/6 3:54 PM
 * @Description: 对象缓存操作 队列或自增等.
 **/
@Component
public class RedisService {

    @Autowired
    @Qualifier(value = "redisTemplate")
    private RedisTemplate redisTemplate;

}
