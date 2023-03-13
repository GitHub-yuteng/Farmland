package com.harvest.oms.service.order.handler.feature.platform;

import com.harvest.core.enums.oms.OrderSourceEnum;
import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.domain.order.feature.platform.OrderPlatformFeature;
import com.harvest.oms.domain.order.feature.platform.PlatformFeature;
import com.harvest.oms.domain.order.feature.platform.PlatformFeature_JD;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @Author: Alodi
 * @Date: 2023/1/28 4:34 PM
 * @Description:
 **/
@Component
public class OrderPlatformFeature_JD_Handler extends AbstractOrderPlatformFeatureHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderPlatformFeature_JD_Handler.class);

    private static final OrderSourceEnum JD = OrderSourceEnum.JD;

    @Override
    public void batchFeatureFill(Long companyId, Collection<OrderInfoDO> orders) {
        Collection<OrderInfoDO> filter = this.filter(companyId, JD, orders);
        if (CollectionUtils.isEmpty(filter)) {
            return;
        }

        filter.forEach(order -> {
            OrderPlatformFeature<PlatformFeature_JD> orderPlatformFeature = new OrderPlatformFeature<>();

            PlatformFeature_JD featureJd = new PlatformFeature_JD();
            this.buildFeature(companyId, featureJd);

            orderPlatformFeature.setCompanyId(companyId);
            orderPlatformFeature.setFeature(featureJd);
            order.setPlatformFeature(orderPlatformFeature);
        });
    }

    @Override
    public <T extends PlatformFeature> void buildFeature(Long companyId, T feature) {
        feature.setCompanyId(companyId);
        feature.setOrderSource(JD);
    }
}
