package com.harvest.core.service.mq;

import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: Alodi
 * @Date: 2023/2/8 10:37 PM
 * @Description: TODO
 **/
@Component
public class ProducerMessageService {

    @Autowired(required = false)
    public RocketMQTemplate rocketMQTemplate;

    public SendResult syncSend(String destination, Object payload) {
        return rocketMQTemplate.syncSend(destination, payload, 10000);
    }

}
