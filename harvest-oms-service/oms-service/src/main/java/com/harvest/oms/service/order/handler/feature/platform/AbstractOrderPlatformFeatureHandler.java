package com.harvest.oms.service.order.handler.feature.platform;

import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.service.order.handler.OrderPlatformFeatureHandler;

import java.util.Collection;

/**
 * @Author: Alodi
 * @Date: 2023/1/28 5:54 PM
 * @Description: TODO
 **/
public abstract class AbstractOrderPlatformFeatureHandler implements OrderPlatformFeatureHandler {

    @Override
    public void batchFeatureFill(Long companyId, Collection<OrderInfoDO> orders) {

    }
}
