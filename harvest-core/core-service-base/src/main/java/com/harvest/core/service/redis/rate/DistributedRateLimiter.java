package com.harvest.core.service.redis.rate;

import lombok.Data;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.Collections;

@Data
public class DistributedRateLimiter {

    private RedisTemplate<String, Object> redisTemplate;
    private DefaultRedisScript<Long> rateLimiterLuaScript;
    private String rateLimiterKey;

    public static DistributedRateLimiter build(RedisTemplate<String, Object> redisTemplate, String rateLimiterKey, String lua) {
        DistributedRateLimiter limiter = new DistributedRateLimiter();
        limiter.redisTemplate = redisTemplate;
        limiter.rateLimiterKey = rateLimiterKey;
        DefaultRedisScript<Long> rateLimiterLuaScript = new DefaultRedisScript<>();
        limiter.setRateLimiterLuaScript(rateLimiterLuaScript);
        rateLimiterLuaScript.setResultType(Long.class);
        rateLimiterLuaScript.setScriptText(lua);
//        rateLimiterLuaScript.setScriptText("" +
//                "local key = KEYS[1];" +
//                "local limit = tonumber(ARGV[1]);" +
//                "local expire_time = tonumber(ARGV[2]);" +
//                "local current_time = tonumber(ARGV[3]);" +
//                "local count = tonumber(redis.call('get', key) or 0);" +
//                "if count + 1 > limit then " +
//                "    return -1;" +
//                "else" +
//                "    redis.call('incr', key);" +
//                "    if current_time + expire_time > redis.call('ttl', key) then " +
//                "        redis.call('expire', key, expire_time);" +
//                "    end " +
//                "    return count + 1;" +
//                "end");
        return limiter;
    }

    public boolean acquire(int limit, int expireTimeSeconds) {
        long currentTimeMillis = System.currentTimeMillis();
        Long result = redisTemplate.execute(rateLimiterLuaScript,
                Collections.singletonList(rateLimiterKey),
                String.valueOf(limit), String.valueOf(expireTimeSeconds),
                String.valueOf(currentTimeMillis));
        return result != null && result != -1 && result <= limit;
    }
}