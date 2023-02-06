package com.harvest.basic.client.profile;

import com.harvest.basic.client.constants.HarvestBasicApplications;
import com.harvest.basic.repository.enums.WebConfigEnum;
import com.harvest.basic.service.redis.key.WebConfigKey;
import com.harvest.core.annotation.feign.HarvestService;
import com.harvest.core.service.redis.CacheService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: Alodi
 * @Date: 2023/1/8 11:54 PM
 * @Description: TODO
 **/
@HarvestService(path = HarvestBasicApplications.Path.WEB_CONFIG)
public class WebConfigClientImpl implements WebConfigClient {

    @Autowired
    private CacheService cacheService;

    @Override
    public String get(Long companyId, WebConfigEnum type) {
        String key = companyId.toString() + "-" + type.getKey();
        return cacheService.get(WebConfigKey.WEB_CONFIG_KEY, key);
    }

    @Override
    public void save(Long companyId, WebConfigEnum type, String value) {
        String key = companyId.toString() + "-" + type.getKey();
        cacheService.set(WebConfigKey.WEB_CONFIG_KEY, key, value);
    }

}
