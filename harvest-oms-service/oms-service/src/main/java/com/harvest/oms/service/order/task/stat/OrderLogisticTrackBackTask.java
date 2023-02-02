package com.harvest.oms.service.order.task.stat;

import java.util.concurrent.Callable;

/**
 * @Author: Alodi
 * @Date: 2023/2/2 9:47 AM
 * @Description: TODO
 **/
public class OrderLogisticTrackBackTask implements Callable<Boolean> {

    private final Long companyId;

    public OrderLogisticTrackBackTask(Long companyId) {
        this.companyId = companyId;
    }

    @Override
    public Boolean call() throws Exception {
        System.out.println(companyId + "物流追踪！");
        return true;
    }

}
