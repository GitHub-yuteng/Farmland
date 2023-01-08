package com.harvest.basic.client.config;

import com.harvest.basic.client.constants.HarvestBasicApplications;
import com.harvest.basic.repository.enums.WebConfigEnum;
import com.harvest.core.feign.annotation.HarvestService;

/**
 * @Author: Alodi
 * @Date: 2023/1/8 11:54 PM
 * @Description: TODO
 **/
@HarvestService(path = HarvestBasicApplications.Path.BASIC_PATH)
public class WebConfigClientImpl implements WebConfigClient {

    @Override
    public String get(Long companyId, WebConfigEnum webConfig) {
        return "ok";
    }
}
