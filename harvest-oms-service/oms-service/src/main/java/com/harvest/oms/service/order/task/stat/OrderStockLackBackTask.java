package com.harvest.oms.service.order.task.stat;

import java.util.concurrent.Callable;

/**
 * @Author: Alodi
 * @Date: 2023/2/2 9:47 AM
 * @Description: TODO
 **/
public class OrderStockLackBackTask implements Callable<Boolean> {

    private final Long companyId;

    public OrderStockLackBackTask(Long companyId) {
        this.companyId = companyId;
    }

    @Override
    public Boolean call() throws Exception {
        System.out.println(companyId + "缺货统计！");
        return true;
    }

}
