package com.harvest.oms.service.order.handler.feature.company;

import com.google.common.collect.Sets;
import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.domain.order.company.CompanyFeature;
import com.harvest.oms.service.order.handler.OrderCompanyFeatureHandler;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;

/**
 * @Author: Alodi
 * @Date: 2023/1/28 4:53 PM
 * @Description: 测试公司订单特性处理
 **/
@Order
@Component
public class OrderCompanyFeature_Test_Handler extends AbstractOrderCompanyFeatureHandler implements OrderCompanyFeatureHandler {

    /**
     * 测试公司特性处理
     */
    private final static Set<Long> TEST_COMPANY_SET = Sets.newHashSet(0L);

    @Override
    public boolean match(Long companyId) {
        return TEST_COMPANY_SET.contains(companyId);
    }

    @Override
    public void batchFeatureFill(Long companyId, Collection<OrderInfoDO> orders) {

    }

    @Override
    public <T extends CompanyFeature> void buildFeature(Long companyId, T feature) {

    }

}
