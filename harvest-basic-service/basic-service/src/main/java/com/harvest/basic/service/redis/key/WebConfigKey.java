package com.harvest.basic.service.redis.key;

import com.harvest.basic.service.redis.BasicKeyPrefix;
import com.harvest.core.service.redis.prefix.KeyModePrefix;

/**
 * @Author: Alodi
 * @Date: 2023/2/6 4:42 PM
 * @Description: TODO
 **/
public class WebConfigKey extends BasicKeyPrefix {

    public WebConfigKey(String keyPrefix) {
        super(keyPrefix);
    }

    public WebConfigKey(String keyPrefix, int expireSeconds) {
        super(keyPrefix, expireSeconds);
    }

    public static final WebConfigKey WEB_CONFIG_KEY = new WebConfigKey(KeyModePrefix.Basic.WEBCONFIG, ONE_HOUR);

}
