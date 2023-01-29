package com.harvest.oms.service.order.handler.feature.company;

import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.service.order.handler.OrderCompanyFeatureHandler;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @Author: Alodi
 * @Date: 2023/1/28 4:53 PM
 * @Description: 公司订单特性处理 测试
 **/
@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class OrderCompanyFeature_Farmland_Handler extends AbstractOrderCompanyFeatureHandler implements OrderCompanyFeatureHandler {

    public final static Long COMPANY_FARMLAND_ID = 123L;

    @Override
    public boolean match(Long companyId) {
        return COMPANY_FARMLAND_ID.equals(companyId);
    }

    @Override
    public void batchFeatureFill(Long companyId, Collection<OrderInfoDO> orders) {

    }

}
