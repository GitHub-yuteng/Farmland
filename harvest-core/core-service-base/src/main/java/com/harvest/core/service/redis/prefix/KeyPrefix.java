package com.harvest.core.service.redis.prefix;

/**
 * @Author: Alodi
 * @Date: 2023/2/2 12:52 PM
 * @Description: TODO
 **/
public interface KeyPrefix {

    /**
     * 过期时间
     */
    int expireSeconds();

    /**
     * 获取key前缀
     */
    String getKeyPrefix();

    /**
     *
     * 获取 key-lock 前缀
     */
    String getKeyLock();
}
