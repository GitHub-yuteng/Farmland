package com.harvest.oms.service.order.handler.feature.platform;

import com.harvest.oms.domain.order.feature.platform.PlatformFeature;

/**
 * @Author: Alodi
 * @Date: 2023/1/28 5:54 PM
 * @Description: TODO
 **/
public abstract class AbstractOrderPlatformFeatureHandler implements OrderPlatformFeatureHandler {

    /**
     * 构建对应平台特性对象
     *
     * @param companyId
     * @param feature
     * @param <T>
     */
    public abstract <T extends PlatformFeature> void buildFeature(Long companyId, T feature);

}
