package com.harvest.basic.service.logistics;

import com.harvest.basic.client.logistics.PlatformLogisticsClient;
import com.harvest.core.engine.PlatformServiceEngine;
import com.harvest.core.enums.platform.PlatformDefinitionEnum;

/**
 * @Author: Alodi
 * @Date: 2023/2/3 12:51 PM
 * @Description: 物流商服务抽象类
 **/
public abstract class AbstractPlatformLogisticsService extends PlatformServiceEngine {

    @Override
    protected PlatformLogisticsClient call(int platformType) {
        return (PlatformLogisticsClient) super.getService(PlatformDefinitionEnum.LOGISTICS, platformType);
    }

}
