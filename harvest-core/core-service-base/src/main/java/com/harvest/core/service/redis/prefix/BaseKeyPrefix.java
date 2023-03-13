package com.harvest.core.service.redis.prefix;

import lombok.Data;

import java.util.Random;

/**
 * @Author: Alodi
 * @Date: 2023/2/2 12:53 PM
 * @Description: TODO
 **/
@Data
public abstract class BaseKeyPrefix implements KeyPrefix {

    private static final Random RANDOM = new Random();

    protected static final int TEN_SECONDS  = 10;
    protected static final int ONE_MINUTE   = 60;
    protected static final int ONE_HOUR     = 60 * 60;
    protected static final int ONE_DAY      = 60 * 60 * 24;
    protected static final int ONE_WEEK     = 60 * 60 * 24 * 7;
    protected static final int ONE_MONTH    = 60 * 60 * 24 * 30;
    protected static final int TWO_MONTH    = 60 * 60 * 24 * 30 * 2;

    /**
     * 分隔符
     */
    private static final String SPLIT = ":";

    /**
     * lock tag
     */
    private static final String LOCK = "-lock:";

    /**
     * key前缀
     */
    private final String keyPrefix;
    /**
     * 过期时间
     */
    private final int expireSeconds;

    public BaseKeyPrefix(String keyPrefix) {
        this(keyPrefix, 0);
    }

    public BaseKeyPrefix(String keyPrefix, int expireSeconds) {
        this.keyPrefix = keyPrefix;
        this.expireSeconds = expireSeconds;
    }

    @Override
    public int expireSeconds() {
        return this.expireSeconds;
    }

    @Override
    public String getKeyPrefix() {
        return this.getClass().getSimpleName() + SPLIT + this.keyPrefix;
    }

    @Override
    public String getKeyLock() {
        return this.getClass().getSimpleName() + SPLIT + this.keyPrefix + LOCK;
    }
}
