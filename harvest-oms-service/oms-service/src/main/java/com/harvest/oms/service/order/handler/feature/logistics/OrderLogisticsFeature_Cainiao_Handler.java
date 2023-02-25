package com.harvest.oms.service.order.handler.feature.logistics;

import com.harvest.core.enums.logistics.LogisticsEnum;
import com.harvest.core.enums.logistics.feature.LogisticsFeature;
import com.harvest.core.enums.logistics.feature.LogisticsFeature_Cainiao;
import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.domain.order.feature.logistics.OrderLogisticsFeature;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @Author: Alodi
 * @Date: 2023/2/25 10:14 PM
 * @Description: TODO
 **/
@Component
public class OrderLogisticsFeature_Cainiao_Handler extends AbstractOrderLogisticsFeatureHandler {

    /**
     * @param companyId
     * @param orders
     */
    @Override
    public void batchFeatureFill(Long companyId, Collection<OrderInfoDO> orders) {
        Collection<OrderInfoDO> filter = this.filter(companyId, LogisticsEnum.CAINIAO, orders);
        if (CollectionUtils.isEmpty(filter)) {
            return;
        }
        filter.forEach(order -> {
            OrderLogisticsFeature<LogisticsFeature_Cainiao> orderLogisticsFeature = new OrderLogisticsFeature<>();
            LogisticsFeature_Cainiao feature_cainiao = new LogisticsFeature_Cainiao();
            this.buildFeature(companyId, feature_cainiao);

            orderLogisticsFeature.setCompanyId(companyId);
            orderLogisticsFeature.setFeature(feature_cainiao);
            order.setLogisticsFeature(orderLogisticsFeature);
        });
    }

    /**
     * 构建对应平台特性对象
     *
     * @param companyId
     * @param feature
     */
    @Override
    public <T extends LogisticsFeature> void buildFeature(Long companyId, T feature) {
        LogisticsFeature_Cainiao logisticsFeature_cainiao = new LogisticsFeature_Cainiao();
        logisticsFeature_cainiao.setLogisticsEnum(LogisticsEnum.CAINIAO);
    }
}
