package com.harvest.oms.service.order.handler.feature.platform;

import com.harvest.core.enums.oms.OrderSourceEnum;
import com.harvest.oms.domain.order.OrderInfoDO;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @Author: Alodi
 * @Date: 2023/1/28 3:50 PM
 * @Description: 平台订单特性处理
 **/
public interface OrderPlatformFeatureHandler {

    /**
     * 匹配对应平台处理器
     *
     * @param companyId
     * @return
     */
    default Collection<OrderInfoDO> filter(Long companyId, OrderSourceEnum orderSourceEnum, Collection<OrderInfoDO> orders) {
        return orders.stream().filter(order -> orderSourceEnum.equals(order.getOrderSource())).collect(Collectors.toList());
    }

    /**
     * 批量填充平台特性
     *
     * @param companyId
     * @param orders
     */
    void batchFeatureFill(Long companyId, Collection<OrderInfoDO> orders);

    /**
     * 默认执行
     *
     * @param companyId
     * @param orderSourceEnum
     * @param orders
     */
    default void execute(Long companyId, OrderSourceEnum orderSourceEnum, Collection<OrderInfoDO> orders) {
        Collection<OrderInfoDO> filter = this.filter(companyId, orderSourceEnum, orders);
        if (CollectionUtils.isNotEmpty(filter)) {
            this.batchFeatureFill(companyId, filter);
        }
    }
}
