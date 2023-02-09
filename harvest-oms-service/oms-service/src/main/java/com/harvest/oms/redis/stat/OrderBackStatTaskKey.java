package com.harvest.oms.redis.stat;

import com.harvest.core.service.redis.prefix.KeyModePrefix;
import com.harvest.oms.redis.OmsKeyPrefix;
import com.harvest.oms.service.order.task.OrderBackStatTask;

/**
 * @Author: Alodi
 * @Date: 2023/2/2 2:31 PM
 * @Description: 订单后台任务缓存 Key 值
 * oms:OrderBackStatTaskKey:back-stat:task-name
 **/
public class OrderBackStatTaskKey extends OmsKeyPrefix {

    public OrderBackStatTaskKey(String keyPrefix) {
        super(keyPrefix);
    }

    public OrderBackStatTaskKey(String keyPrefix, int expireSeconds) {
        super(keyPrefix, expireSeconds);
    }

    /**
     * 时间最小间隔
     */
    public final static OrderBackStatTaskKey INTERVAL_LIMIT = new OrderBackStatTaskKey(
            KeyModePrefix.OMS.ORDER_BACK_STAT_TASK + "interval-limit", ONE_MINUTE
    );

    /**
     * 缺货标记后台任务
     */
    public final static OrderBackStatTaskKey STOCK_LACK_KEY = new OrderBackStatTaskKey(
            KeyModePrefix.OMS.ORDER_BACK_STAT_TASK + OrderBackStatTask.BackStatTaskEnum.STOCK_LACK.taskName, ONE_MINUTE
    );

    /**
     * 物流追踪后台任务
     */
    public final static OrderBackStatTaskKey LOGISTICS_TRACKING_KEY = new OrderBackStatTaskKey(
            KeyModePrefix.OMS.ORDER_BACK_STAT_TASK + OrderBackStatTask.BackStatTaskEnum.LOGISTICS_TRACKING.taskName, ONE_MINUTE
    );

}
