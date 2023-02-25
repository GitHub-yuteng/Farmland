package com.harvest.oms.service.order.handler.feature.company;

import com.harvest.oms.domain.order.feature.company.CompanyFeature;

/**
 * @Author: Alodi
 * @Date: 2023/1/28 5:54 PM
 * @Description: TODO
 **/
public abstract class AbstractOrderCompanyFeatureHandler implements OrderCompanyFeatureHandler {

    /**
     * 构建对应平台特性对象
     *
     * @param companyId
     * @param feature
     * @param <T>
     */
    public abstract <T extends CompanyFeature> void buildFeature(Long companyId, T feature);
}
