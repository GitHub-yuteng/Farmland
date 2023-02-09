package com.harvest.oms.service.order.task;

import com.harvest.core.service.redis.CacheService;
import com.harvest.oms.service.order.task.stat.OrderLogisticTrackBackTask;
import com.harvest.oms.service.order.task.stat.OrderStockLackBackTask;
import com.harvest.oms.redis.stat.OrderBackStatTaskKey;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;

/**
 * @Author: Alodi
 * @Date: 2023/2/1 8:37 PM
 * @Description: 后台轻量级任务
 **/
@Component
public class OrderBackStatTaskContext implements OrderBackStatTask, InitializingBean {

    /**
     * 订单物流追踪
     */
    private AbstractBackTaskProcessor LogisticsTrackingBackTaskProcessor;

    /**
     * 缺货
     */
    private AbstractBackTaskProcessor StockLackBackTaskProcessor;

    @Autowired
    private CacheService cacheService;

    @Override
    public void afterPropertiesSet() {

        LogisticsTrackingBackTaskProcessor = new AbstractBackTaskProcessor(BackStatTaskEnum.LOGISTICS_TRACKING.taskName, 10, 10, cacheService) {
            @Override
            protected Callable<Boolean> getTask(long companyId) {
                return new OrderLogisticTrackBackTask(companyId);
            }
        };

        StockLackBackTaskProcessor = new AbstractBackTaskProcessor(BackStatTaskEnum.STOCK_LACK.taskName, 10, 10, cacheService) {
            @Override
            protected Callable<Boolean> getTask(long companyId) {
                return new OrderStockLackBackTask(companyId);
            }
        };
    }

    @Override
    public void StockLackStat(long companyId) {
        StockLackBackTaskProcessor.execute(companyId, OrderBackStatTaskKey.STOCK_LACK_KEY);
    }

    @Override
    public void LogisticsTracking(long companyId) {
        LogisticsTrackingBackTaskProcessor.execute(companyId, OrderBackStatTaskKey.LOGISTICS_TRACKING_KEY);
    }

}
