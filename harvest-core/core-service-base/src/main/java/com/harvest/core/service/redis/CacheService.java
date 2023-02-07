package com.harvest.core.service.redis;

import com.harvest.core.service.redis.prefix.KeyPrefix;
import com.harvest.core.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Alodi
 * @Date: 2023/2/6 3:54 PM
 * @Description: 非对象操作，缓存服务
 **/
@Component
public class CacheService {

    @Autowired
    @Qualifier(value = "stringRedisTemplate")
    private StringRedisTemplate stringRedisTemplate;

    public <T> T get(KeyPrefix prefix, String key, Class<T> clazz) {
        String realKey = prefix.getKeyPrefix() + key;
        String str = stringRedisTemplate.opsForValue().get(realKey);
        return JsonUtils.json2Object(str, clazz);
    }

    public String get(KeyPrefix prefix, String key) {
        String realKey = prefix.getKeyPrefix() + key;
        return stringRedisTemplate.opsForValue().get(realKey);
    }

    /**
     * 设置对象
     */
    public <T> void set(KeyPrefix prefix, String key, T value) {
        String str = JsonUtils.object2Json(value);
        if (str == null || str.length() <= 0) {
            return;
        }
        //生成真正的key
        String realKey = prefix.getKeyPrefix() + key;
        int seconds = prefix.expireSeconds();
        if (seconds <= 0) {
            stringRedisTemplate.opsForValue().set(realKey, str);
        } else {
            stringRedisTemplate.opsForValue().set(realKey, str, seconds, TimeUnit.SECONDS);
        }
    }

    /**
     * 设置对象
     */
    public <T> void set(KeyPrefix prefix, String key, String value) {
        if (value == null || value.length() <= 0) {
            return;
        }
        //生成真正的key
        String realKey = prefix.getKeyPrefix() + key;
        int seconds = prefix.expireSeconds();
        if (seconds <= 0) {
            stringRedisTemplate.opsForValue().set(realKey, value);
        } else {
            stringRedisTemplate.opsForValue().set(realKey, value, seconds, TimeUnit.SECONDS);
        }
    }

    /**
     * Set key to hold the string value if key is absent.
     * setNx 有key值 set 返回 null, 无key值 相当于 set
     */
    public boolean setNx(KeyPrefix prefix, String key, String value) {
        //生成真正的key
        String realKey = prefix.getKeyPrefix() + key;
        int timeout = prefix.expireSeconds();
        return Boolean.TRUE.equals(stringRedisTemplate.opsForValue().setIfAbsent(realKey, value, timeout, TimeUnit.SECONDS));
    }

    /**
     * Set key to hold the string value if key is present.
     * setNx 有key值 set 覆盖value, 无key值set 返回 null
     */
    public Boolean setXx(KeyPrefix prefix, String key, String value) {
        //生成真正的key
        String realKey = prefix.getKeyPrefix() + key;
        int timeout = prefix.expireSeconds();
        return stringRedisTemplate.opsForValue().setIfPresent(realKey, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     */
    public boolean exists(KeyPrefix prefix, String key) {
        //生成真正的key
        String realKey = prefix.getKeyPrefix() + key;
        return Boolean.TRUE.equals(stringRedisTemplate.hasKey(realKey));
    }

    /**
     * 设置对象过期时间
     */
    public void expire(KeyPrefix prefix, String key) {
        String realKey = prefix.getKeyPrefix() + key;
        int seconds = prefix.expireSeconds();
        stringRedisTemplate.expire(realKey, seconds, TimeUnit.SECONDS);
    }

    /**
     * 删除
     */
    public void delete(KeyPrefix prefix, String key) {
        //生成真正的key
        String realKey = prefix.getKeyPrefix() + key;
        stringRedisTemplate.delete(realKey);
    }

}
