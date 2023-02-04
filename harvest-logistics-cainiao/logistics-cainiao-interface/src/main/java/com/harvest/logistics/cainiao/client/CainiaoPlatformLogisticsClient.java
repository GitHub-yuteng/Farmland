package com.harvest.logistics.cainiao.client;

import com.harvest.core.annotation.Platform;
import com.harvest.core.annotation.feign.HarvestClient;
import com.harvest.core.engine.logistics.PlatformLogisticsClient;
import com.harvest.core.enums.platform.PlatformDefinitionEnum;
import com.harvest.logistics.cainiao.HarvestCainiaoLogisticsApplications;


/**
 * @Author: Alodi
 * @Date: 2023/2/4 2:27 PM
 * @Description: TODO
 **/
@HarvestClient(value = HarvestCainiaoLogisticsApplications.SERVICE_NAME, path = HarvestCainiaoLogisticsApplications.SERVICE_PATH)
@Platform(definition = PlatformDefinitionEnum.LOGISTICS, type = CainiaoPlatformLogisticsClient.PLATFORM_TYPE)
public interface CainiaoPlatformLogisticsClient extends PlatformLogisticsClient {

    /**
     * {@link com.harvest.logistics.enums.LogisticsEnum.index}
     */
    int PLATFORM_TYPE = 1;

}
