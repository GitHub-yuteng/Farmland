package com.harvest.oms.service.order.handler.feature.platform;

import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.domain.order.feature.platform.PlatformFeature;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @Author: Alodi
 * @Date: 2023/1/28 4:34 PM
 * @Description:
 **/
@Component
public class OrderPlatformFeature_Tiktok_Handler extends AbstractOrderPlatformFeatureHandler {

    @Override
    public void batchFeatureFill(Long companyId, Collection<OrderInfoDO> orders) {

    }

    @Override
    public <T extends PlatformFeature> void buildFeature(Long companyId, T feature) {

    }

}
