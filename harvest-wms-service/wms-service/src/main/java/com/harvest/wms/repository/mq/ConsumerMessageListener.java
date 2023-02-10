package com.harvest.wms.repository.mq;

import com.harvest.core.utils.JsonUtils;
import com.harvest.oms.request.order.warehouse.SubmitWmsOrderMessage;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @Author: Alodi
 * @Date: 2023/2/8 10:37 PM
 * @Description: TODO
 **/
@RocketMQMessageListener(topic = "my-topic", consumerGroup = "ORDER-PRODUCER-GROUP")
@Component
public class ConsumerMessageListener implements RocketMQListener<SubmitWmsOrderMessage> {


    @Override
    public void onMessage(SubmitWmsOrderMessage message) {
        System.out.println(JsonUtils.object2Json(message));
    }
}
