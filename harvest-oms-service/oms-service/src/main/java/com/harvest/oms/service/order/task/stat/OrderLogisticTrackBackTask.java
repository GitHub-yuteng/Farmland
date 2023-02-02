package com.harvest.oms.service.order.task.stat;

import java.util.concurrent.Callable;

/**
 * @Author: Alodi
 * @Date: 2023/2/2 9:47 AM
 * @Description: TODO
 **/
public class OrderLogisticTrackBackTask implements Callable<Boolean> {

    public OrderLogisticTrackBackTask(Long companyId) {

    }

    @Override
    public Boolean call() throws Exception {
        System.out.println("物流追踪！");
        return true;
    }

}
