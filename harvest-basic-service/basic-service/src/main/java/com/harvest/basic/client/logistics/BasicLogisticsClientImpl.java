package com.harvest.basic.client.logistics;

import com.harvest.basic.client.constants.HarvestBasicApplications;
import com.harvest.core.annotation.feign.HarvestService;
import com.harvest.core.engine.logistics.PlatformLogisticsClient;

/**
 * @Author: Alodi
 * @Date: 2023/2/3 2:48 PM
 * @Description: 物流驱动
 **/
@HarvestService(path = HarvestBasicApplications.Path.LOGISTICS)
public class BasicLogisticsClientImpl implements BasicLogisticsClient {

    private PlatformLogisticsClient platformLogisticsClient;

    @Override
    public void submitDeclaration(long companyId) {
        platformLogisticsClient.submitDeclaration(companyId);
    }
}
