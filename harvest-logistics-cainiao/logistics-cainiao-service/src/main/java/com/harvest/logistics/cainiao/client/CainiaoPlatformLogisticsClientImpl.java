package com.harvest.logistics.cainiao.client;

import com.harvest.core.annotation.feign.HarvestService;
import com.harvest.logistics.cainiao.HarvestCainiaoLogisticsApplications;

/**
 * @Author: Alodi
 * @Date: 2023/2/4 2:27 PM
 * @Description: TODO
 **/
@HarvestService(path = HarvestCainiaoLogisticsApplications.SERVICE_PATH)
public class CainiaoPlatformLogisticsClientImpl implements CainiaoPlatformLogisticsClient {

    @Override
    public void submitDeclaration(long companyId) {
        System.out.println("菜鸟申报");
    }

}
