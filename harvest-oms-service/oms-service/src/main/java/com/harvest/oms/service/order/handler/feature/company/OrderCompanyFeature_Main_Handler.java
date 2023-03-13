package com.harvest.oms.service.order.handler.feature.company;

import com.google.common.collect.Sets;
import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.domain.order.feature.company.CompanyFeature;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;

/**
 * @Author: Alodi
 * @Date: 2023/1/28 4:53 PM
 * @Description: 公司订单特性处理 重点客户
 **/
@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class OrderCompanyFeature_Main_Handler extends AbstractOrderCompanyFeatureHandler implements OrderCompanyFeatureHandler {

    /**
     * 重点公司特性处理
     */
    protected static final Set<Long> MAIN_COMPANY_SET = Sets.newHashSet(1L, 2L);

    @Override
    public void batchFeatureFill(Long companyId, Collection<OrderInfoDO> orders) {

    }

    @Override
    public <T extends CompanyFeature> void buildFeature(Long companyId, T feature) {

    }

}
