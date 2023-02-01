package com.harvest.oms.service.order.handler.feature.platform;

import com.harvest.core.enums.oms.OrderSourceEnum;
import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.domain.order.platform.OrderPlatformFeature;
import com.harvest.oms.domain.order.platform.PlatformFeature;
import com.harvest.oms.domain.order.platform.PlatformFeature_TMall;
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
public class OrderPlatformFeature_TMallHandler extends AbstractOrderPlatformFeatureHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(OrderPlatformFeature_JDHandler.class);

    private final static OrderSourceEnum TMALL = OrderSourceEnum.TMall;

    @Override
    public void batchFeatureFill(Long companyId, Collection<OrderInfoDO> orders) {
        Collection<OrderInfoDO> filter = this.filter(companyId, TMALL, orders);
        if (CollectionUtils.isEmpty(filter)) {
            return;
        }

        filter.forEach(order -> {
            OrderPlatformFeature<PlatformFeature_TMall> orderPlatformFeature = new OrderPlatformFeature<>();
            PlatformFeature_TMall feature_TMall = new PlatformFeature_TMall();
            feature_TMall.setOrderSource(TMALL);
            feature_TMall.setCompanyId(companyId);
            orderPlatformFeature.setFeature(feature_TMall);
            order.setPlatformFeature(orderPlatformFeature);
        });
    }

    @Override
    public <T extends PlatformFeature> void buildFeature(Long companyId, T feature) {

    }

}
