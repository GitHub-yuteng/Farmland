package com.harvest.oms.service.order.task;

import com.harvest.oms.service.order.task.stat.OrderLogisticTrackBackTask;
import com.harvest.oms.service.order.task.stat.OrderStockLackBackTask;
import com.harvest.oms.service.redis.OrderBackStatTaskKey;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;

/**
 * @Author: Alodi
 * @Date: 2023/2/1 8:37 PM
 * @Description: TODO
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

    @Override
    public void afterPropertiesSet() throws Exception {
        LogisticsTrackingBackTaskProcessor = new AbstractBackTaskProcessor(BackStatTaskEnum.LOGISTICS_TRACKING.taskName, 10, 10) {
            @Override
            protected Callable<Boolean> getTask(long companyId) {
                return new OrderLogisticTrackBackTask(companyId);
            }
        };
        StockLackBackTaskProcessor = new AbstractBackTaskProcessor(BackStatTaskEnum.STOCK_LACK.taskName, 10, 10) {
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
