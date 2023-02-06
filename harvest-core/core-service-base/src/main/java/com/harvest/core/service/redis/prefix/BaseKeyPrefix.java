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

    private final static Random RANDOM = new Random();

    protected final static int ONE_HOUR = 60 * 60;
    protected final static int ONE_DAY  = 60 * 60 * 24;
    protected final static int ONE_WEEK = 60 * 60 * 24 * 7;
    protected final static int ONE_MONTH = 60 * 60 * 24 * 30;
    protected final static int TWO_MONTH = 60 * 60 * 24 * 30 * 2;

    private final static String SPLIT = ":";

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
}
