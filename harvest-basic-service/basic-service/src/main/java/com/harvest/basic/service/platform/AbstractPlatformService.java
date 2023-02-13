package com.harvest.basic.service.platform;

import com.harvest.core.engine.PlatformServiceEngine;
import com.harvest.core.enums.platform.PlatformDefinitionEnum;

/**
 * @Author: Alodi
 * @Date: 2023/2/13 10:02 AM
 * @Description: TODO
 **/
public class AbstractPlatformService extends PlatformServiceEngine {

    @Override
    protected Object call(int platformType) {
        return super.getService(PlatformDefinitionEnum.PLATFORM, platformType);
    }
}
