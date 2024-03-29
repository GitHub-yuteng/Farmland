package com.harvest.logistics.cainiao.client;

import com.harvest.basic.client.logistics.PlatformLogisticsClient;
import com.harvest.core.annotation.Platform;
import com.harvest.core.annotation.feign.HarvestClient;
import com.harvest.core.enums.platform.PlatformDefinitionEnum;
import com.harvest.logistics.cainiao.HarvestCainiaoLogisticsApplications;


/**
 * @Author: Alodi
 * @Date: 2023/2/4 2:27 PM
 * @Description: TODO
 **/
@Platform(definition = PlatformDefinitionEnum.LOGISTICS, type = PlatformCainiaoLogisticsClient.PLATFORM_TYPE)
@HarvestClient(value = HarvestCainiaoLogisticsApplications.SERVICE_NAME, path = HarvestCainiaoLogisticsApplications.SERVICE_PATH)
public interface PlatformCainiaoLogisticsClient extends PlatformLogisticsClient {

    /**
     * {@link com.harvest.core.enums.logistics.LogisticsEnum}
     */
    int PLATFORM_TYPE = 3;

}
