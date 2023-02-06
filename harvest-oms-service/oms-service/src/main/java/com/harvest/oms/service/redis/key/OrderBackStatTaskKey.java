package com.harvest.oms.service.redis.key;

import com.harvest.core.service.redis.prefix.KeyModePrefix;
import com.harvest.oms.service.order.task.OrderBackStatTask;
import com.harvest.oms.service.redis.OmsKeyPrefix;

/**
 * @Author: Alodi
 * @Date: 2023/2/2 2:31 PM
 * @Description: 订单后台任务缓存 Key 值
 **/
public class OrderBackStatTaskKey extends OmsKeyPrefix {

    public OrderBackStatTaskKey(String keyPrefix) {
        super(keyPrefix);
    }

    public OrderBackStatTaskKey(String keyPrefix, int expireSeconds) {
        super(keyPrefix, expireSeconds);
    }

    /**
     * 缺货标记后台任务
     */
    public final static OrderBackStatTaskKey STOCK_LACK_KEY = new OrderBackStatTaskKey(
            KeyModePrefix.OMS.ORDER_BACK_STAT_TASK + OrderBackStatTask.BackStatTaskEnum.STOCK_LACK.taskName
    );

    /**
     * 物流追踪后台任务
     */
    public final static OrderBackStatTaskKey LOGISTICS_TRACKING_KEY = new OrderBackStatTaskKey(
            KeyModePrefix.OMS.ORDER_BACK_STAT_TASK + OrderBackStatTask.BackStatTaskEnum.LOGISTICS_TRACKING.taskName
    );

}
