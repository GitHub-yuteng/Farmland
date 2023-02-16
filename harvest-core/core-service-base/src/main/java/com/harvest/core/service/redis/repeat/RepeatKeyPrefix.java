package com.harvest.core.service.redis.repeat;

import com.harvest.core.service.redis.prefix.BaseKeyPrefix;

/**
 * @Author: Alodi
 * @Date: 2023/2/16 11:09 PM
 * @Description: 公用重复提交校验
 **/
public class RepeatKeyPrefix extends BaseKeyPrefix {

    public RepeatKeyPrefix(String keyPrefix) {
        super(keyPrefix);
    }

    public RepeatKeyPrefix(String keyPrefix, int expireSeconds) {
        super(keyPrefix, expireSeconds);
    }

    /**
     * 重复提交
     */
    public final static RepeatKeyPrefix INTERVAL_REPEAT = new RepeatKeyPrefix("interval-repeat:", ONE_MINUTE);

}
