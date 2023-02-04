package com.harvest.core.engine.logistics;

import com.harvest.core.engine.PlatformServiceEngine;
import com.harvest.core.enums.platform.PlatformDefinitionEnum;

/**
 * @Author: Alodi
 * @Date: 2023/2/3 12:51 PM
 * @Description: 物流商服务抽象类
 **/
public abstract class AbstractPlatformLogisticsService extends PlatformServiceEngine {

    @Override
    protected Object call(int platformType) {
        return super.getService(PlatformDefinitionEnum.LOGISTICS, platformType);
    }


}
