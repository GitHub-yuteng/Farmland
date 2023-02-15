package com.harvest.logistics.jd.client;

import com.harvest.basic.client.logistics.PlatformLogisticsClient;
import com.harvest.core.annotation.Platform;
import com.harvest.core.annotation.feign.HarvestClient;
import com.harvest.core.enums.platform.PlatformDefinitionEnum;
import com.harvest.logistics.jd.HarvestJDLogisticsApplications;


/**
 * @Author: Alodi
 * @Date: 2023/2/4 2:27 PM
 * @Description: TODO
 **/
@Platform(definition = PlatformDefinitionEnum.LOGISTICS, type = PlatformJDLogisticsClient.PLATFORM_TYPE)
@HarvestClient(value = HarvestJDLogisticsApplications.SERVICE_NAME, path = HarvestJDLogisticsApplications.SERVICE_PATH)
public interface PlatformJDLogisticsClient extends PlatformLogisticsClient {

    /**
     * {@link com.harvest.core.enums.logistics.LogisticsEnum}
     */
    int PLATFORM_TYPE = 1;

}
