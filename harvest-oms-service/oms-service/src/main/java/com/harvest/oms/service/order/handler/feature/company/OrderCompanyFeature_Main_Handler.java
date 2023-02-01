package com.harvest.oms.service.order.handler.feature.company;

import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.domain.order.company.CompanyFeature;
import com.harvest.oms.service.order.handler.OrderCompanyFeatureHandler;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @Author: Alodi
 * @Date: 2023/1/28 4:53 PM
 * @Description: 公司订单特性处理 重点客户
 **/
@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class OrderCompanyFeature_Main_Handler extends AbstractOrderCompanyFeatureHandler implements OrderCompanyFeatureHandler {

    @Override
    public void batchFeatureFill(Long companyId, Collection<OrderInfoDO> orders) {

    }

    @Override
    public <T extends CompanyFeature> void buildFeature(Long companyId, T feature) {

    }

}
