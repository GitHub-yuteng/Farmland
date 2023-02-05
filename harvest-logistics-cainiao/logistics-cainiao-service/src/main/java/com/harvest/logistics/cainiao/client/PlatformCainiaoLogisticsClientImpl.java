package com.harvest.logistics.cainiao.client;

import com.harvest.core.annotation.feign.HarvestService;
import com.harvest.logistics.cainiao.HarvestCainiaoLogisticsApplications;

/**
 * @Author: Alodi
 * @Date: 2023/2/4 2:27 PM
 * @Description: TODO
 **/
@HarvestService(path = HarvestCainiaoLogisticsApplications.SERVICE_PATH)
public class PlatformCainiaoLogisticsClientImpl implements PlatformCainiaoLogisticsClient {

    @Override
    public void submitDeclaration(Long companyId) {
        System.out.println("菜鸟申报");
    }

    @Override
    public void print(Long companyId) {
        System.out.println("菜鸟获取面单");
    }
}
