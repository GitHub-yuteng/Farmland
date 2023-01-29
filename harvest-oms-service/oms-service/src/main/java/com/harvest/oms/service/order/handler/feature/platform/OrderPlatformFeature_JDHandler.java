package com.harvest.oms.service.order.handler.feature.platform;

import com.harvest.core.enums.oms.OrderSourceEnum;
import com.harvest.oms.domain.order.OrderInfoDO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @Author: Alodi
 * @Date: 2023/1/28 4:34 PM
 * @Description:
 **/
@Component
public class OrderPlatformFeature_JDHandler extends AbstractOrderPlatformFeatureHandler {

    private final static OrderSourceEnum JD = OrderSourceEnum.JD;

    @Override
    public void batchFeatureFill(Long companyId, Collection<OrderInfoDO> orders) {
        Collection<OrderInfoDO> filter = this.filter(companyId, JD, orders);
        if (CollectionUtils.isEmpty(filter)) {
            return;
        }

        filter.forEach(order -> {

        });
    }

}
