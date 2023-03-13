package com.harvest.oms.service.order.log;

import com.harvest.core.log.OperationLog;
import com.harvest.core.service.biz.BizOperationLog;
import com.harvest.oms.domain.order.log.OrderOperationLog;
import org.springframework.stereotype.Component;

/**
 * @Author: Alodi
 * @Date: 2023/3/13 3:16 PM
 * @Description: TODO
 **/
@Component
public class OrderOperationLogger implements BizOperationLog<OrderOperationLog> {

    /**
     * 匹配
     */
    @Override
    public Class<OrderOperationLog> match() {
        return OrderOperationLog.class;
    }

    /**
     * 保存
     *
     * @param log
     */
    @Override
    public void store(OperationLog log) {
        OrderOperationLog operationLog = (OrderOperationLog) log;
        System.out.println(operationLog.toString());
    }
}
