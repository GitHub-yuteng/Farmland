package com.harvest.core.engine.track;

import com.harvest.core.engine.PlatformServiceEngine;
import com.harvest.core.enums.platform.PlatformDefinitionEnum;

/**
 * @Author: Alodi
 * @Date: 2023/2/3 12:51 PM
 * @Description: 物流追踪商
 **/
public abstract class AbstractPlatformLogisticsTrackService extends PlatformServiceEngine {

    @Override
    protected Object call(int platformType) {
        return super.getService(PlatformDefinitionEnum.LOGISTICS_TRACK, platformType);
    }

}
