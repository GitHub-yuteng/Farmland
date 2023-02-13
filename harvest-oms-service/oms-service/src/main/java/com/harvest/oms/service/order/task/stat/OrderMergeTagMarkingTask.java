package com.harvest.oms.service.order.task.stat;

import java.util.concurrent.Callable;

/**
 * @Author: Alodi
 * @Date: 2023/2/13 5:08 PM
 * @Description: TODO
 **/
public class OrderMergeTagMarkingTask implements Callable<Boolean> {

    private final Long companyId;

    public OrderMergeTagMarkingTask(Long companyId) {
        this.companyId = companyId;
    }

    @Override
    public Boolean call() throws Exception {
        System.out.println(companyId + "订单合单！");
        return true;
    }
}
