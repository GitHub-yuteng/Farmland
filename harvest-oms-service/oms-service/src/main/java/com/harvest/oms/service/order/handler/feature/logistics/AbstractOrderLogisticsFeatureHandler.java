package com.harvest.oms.service.order.handler.feature.logistics;

import com.harvest.core.enums.logistics.feature.LogisticsFeature;

/**
 * @Author: Alodi
 * @Date: 2023/2/25 5:54 PM
 * @Description: TODO
 **/
public abstract class AbstractOrderLogisticsFeatureHandler implements OrderLogisticsFeatureHandler {


    /**
     * 构建对应平台特性对象
     *
     * @param companyId
     * @param feature
     * @param <T>
     */
    public abstract <T extends LogisticsFeature> void buildFeature(Long companyId, T feature);

}
