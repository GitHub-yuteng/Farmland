package com.harvest.oms.service.order.task;

import com.harvest.core.service.redis.CacheService;
import com.harvest.oms.redis.stat.OrderBackStatTaskKey;
import com.harvest.oms.service.order.task.stat.OrderLogisticTrackBackTask;
import com.harvest.oms.service.order.task.stat.OrderMergeTagMarkingTask;
import com.harvest.oms.service.order.task.stat.OrderStockLackBackTask;
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
     * 订单缺货打标处理器
     */
    private AbstractBackTaskProcessor StockLackBackTaskProcessor;

    /**
     * 订单物流追踪处理器
     */
    private AbstractBackTaskProcessor LogisticsTrackingBackTaskProcessor;

    /**
     * 订单合单打标处理器
     */
    private AbstractBackTaskProcessor MergeTagMarkingTaskProcessor;

    @Autowired
    private CacheService cacheService;

    @Override
    public void afterPropertiesSet() {
        this.initBackTaskProcessors();
    }

    private void initBackTaskProcessors() {

        StockLackBackTaskProcessor = new AbstractBackTaskProcessor(
                BackStatTaskEnum.STOCK_LACK.taskName,
                DEFAULT_10,
                DEFAULT_10,
                cacheService
        ) {
            @Override
            protected Callable<Boolean> getTask(Long companyId) {
                return new OrderStockLackBackTask(companyId);
            }
        };

        LogisticsTrackingBackTaskProcessor = new AbstractBackTaskProcessor(
                BackStatTaskEnum.LOGISTICS_TRACKING.taskName,
                DEFAULT_10,
                DEFAULT_10,
                cacheService
        ) {
            @Override
            protected Callable<Boolean> getTask(Long companyId) {
                return new OrderLogisticTrackBackTask(companyId);
            }
        };

        MergeTagMarkingTaskProcessor = new AbstractBackTaskProcessor(
                BackStatTaskEnum.MERGE_TAG_MARKING.taskName,
                DEFAULT_10,
                DEFAULT_10,
                cacheService
        ) {
            @Override
            protected Callable<Boolean> getTask(Long companyId) {
                return new OrderMergeTagMarkingTask(companyId);
            }
        };
    }

    @Override
    public void StockLackStat(Long companyId) {
        StockLackBackTaskProcessor.execute(companyId, OrderBackStatTaskKey.STOCK_LACK_KEY);
    }

    @Override
    public void LogisticsTrackingStat(Long companyId) {
        LogisticsTrackingBackTaskProcessor.execute(companyId, OrderBackStatTaskKey.LOGISTICS_TRACKING_KEY);
    }

    @Override
    public void OrderMergeTagMarkingStat(Long companyId) {
        MergeTagMarkingTaskProcessor.execute(companyId, OrderBackStatTaskKey.MERGE_TAG_MARKING_KEY);
    }

}
