package com.harvest.oms.service.order.handler.section;

import com.harvest.core.utils.JsonUtils;
import com.harvest.oms.client.logistics.LogisticsReadClient;
import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.domain.order.logistics.OrderLogisticsChannelDO;
import com.harvest.oms.repository.domain.logistics.OrderLogisticsKey;
import com.harvest.oms.service.order.handler.OrderSectionHandler;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author: Alodi
 * @Date: 2023/1/8 1:34 AM
 * @Description: 订单物流信息
 **/
@Order(OrderSectionHandler.Order.ORDER_LOGISTICS)
@Component
public class OrderLogisticsSectionHandler implements OrderSectionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderLogisticsSectionHandler.class);

    @Autowired
    private LogisticsReadClient logisticsReadClient;

    /**
     * @param companyId
     * @param order
     */
    @Override
    public void fill(Long companyId, OrderInfoDO order) {
        this.batchFill(companyId, Collections.singleton(order));
    }

    /**
     * @param companyId
     * @param orders
     */
    @Override
    public void batchFill(Long companyId, Collection<OrderInfoDO> orders) {
        List<OrderLogisticsKey> logisticsKeys = orders.stream()
                .filter(order -> Objects.nonNull(order.getCarrierId()) && Objects.nonNull(order.getChannelId()))
                .map(order -> {
                    OrderLogisticsKey logisticsKey = new OrderLogisticsKey();
                    logisticsKey.setCarrierId(companyId);
                    logisticsKey.setChannelId(order.getCarrierId());
                    logisticsKey.setCompanyId(order.getChannelId());
                    return logisticsKey;
                }).collect(Collectors.toList());

        if (CollectionUtils.isEmpty(logisticsKeys)) {
            return;
        }

        Collection<OrderLogisticsChannelDO> channels = logisticsReadClient.getChanelByLogisticsKeys(companyId, logisticsKeys);
        if (CollectionUtils.isEmpty(channels)) {
            LOGGER.error("OrderLogisticsSectionHandler#batchFill#承运商渠道信息为空！logisticsKeys:{}", JsonUtils.object2Json(logisticsKeys));
            return;
        }
        Map<String, OrderLogisticsChannelDO> logisticsKeyMap = channels.stream().collect(
                Collectors.toMap(channel -> channel.getCarrierId() + "|" + channel.getChannelId(), Function.identity(), (k1, k2) -> k1)
        );
        orders.forEach(order -> {
            if (Objects.isNull(order.getCarrierId()) || Objects.isNull(order.getChannelId())) {
                return;
            }
            Long carrierId = order.getCarrierId();
            Long channelId = order.getChannelId();
            OrderLogisticsChannelDO channel = logisticsKeyMap.get(carrierId + "|" + channelId);
            if (Objects.isNull(channel)) {
                LOGGER.error("OrderLogisticsSectionHandler#batchFill#承运商渠道信息异常！carrierId:{}, channelId:{}", carrierId, channelId);
                return;
            }
            order.setLogisticsChannel(channel);
        });
    }
}
