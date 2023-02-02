package com.harvest.oms.service.redis;

import com.harvest.oms.service.order.task.OrderBackStatTask;

/**
 * @Author: Alodi
 * @Date: 2023/2/2 2:31 PM
 * @Description: TODO
 **/
public class OrderBackStatTaskKey extends OmsKeyPrefix {

    public OrderBackStatTaskKey(String keyPrefix) {
        super(keyPrefix);
    }

    public OrderBackStatTaskKey(String keyPrefix, int expireSeconds) {
        super(keyPrefix, expireSeconds);
    }

    public final static OrderBackStatTaskKey STOCK_LACK_KEY = new OrderBackStatTaskKey(OrderBackStatTask.BackStatTaskEnum.STOCK_LACK.taskName);

    public final static OrderBackStatTaskKey LOGISTICS_TRACKING_KEY = new OrderBackStatTaskKey(OrderBackStatTask.BackStatTaskEnum.LOGISTICS_TRACKING.taskName);

}
