package com.harvest.core.engine.warehouse;

import com.harvest.core.engine.PlatformServiceEngine;
import com.harvest.core.enums.platform.PlatformDefinitionEnum;

/**
 * @Author: Alodi
 * @Date: 2023/2/3 12:51 PM
 * @Description: 仓储服务商
 **/
public abstract class AbstractPlatformWarehouseService extends PlatformServiceEngine {

    @Override
    protected Object call(int platformType) {
        return super.getService(PlatformDefinitionEnum.WAREHOUSE, platformType);
    }

}
