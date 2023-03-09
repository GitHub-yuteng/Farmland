package com.harvest.wms.service.mq;

import com.harvest.core.service.mq.topic.MessageTopic;
import com.harvest.core.utils.JsonUtils;
import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.request.order.warehouse.SubmitWmsOrderMessage;
import com.harvest.wms.client.callback.order.CallBackOrderClient;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * @Author: Alodi
 * @Date: 2023/2/8 10:37 PM
 * @Description: TODO
 **/
@RocketMQMessageListener(topic = MessageTopic.ORDER_PUSH_WMS, consumerGroup = "ORDER-PRODUCER-GROUP")
@Component
public class ConsumerMessageListener implements RocketMQListener<SubmitWmsOrderMessage> {

    @Autowired
    private CallBackOrderClient callBackOrderClient;

    @Override
    public void onMessage(SubmitWmsOrderMessage message) {
        System.out.println(JsonUtils.object2Json(message));
        OrderInfoDO order = message.getOrder();
        callBackOrderClient.returnAudit(order.companyId, Collections.singleton(order.getOrderId()));
    }
}
