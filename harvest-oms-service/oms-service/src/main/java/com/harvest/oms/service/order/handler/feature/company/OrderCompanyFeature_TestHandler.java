package com.harvest.oms.service.order.handler.feature.company;

import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.service.order.handler.OrderCompanyFeatureHandler;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @Author: Alodi
 * @Date: 2023/1/28 4:53 PM
 * @Description: 公司订单特性处理 测试
 **/
@Component
public class OrderCompanyFeature_TestHandler implements OrderCompanyFeatureHandler {

    @Override
    public void batchFeatureFill(Long companyId, Collection<OrderInfoDO> orders) {

    }

}
