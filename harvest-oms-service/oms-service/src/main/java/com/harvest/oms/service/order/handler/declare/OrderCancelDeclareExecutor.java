package com.harvest.oms.service.order.handler.declare;

import com.harvest.oms.domain.order.OrderInfoDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author: Alodi
 * @Date: 2023/2/16 3:44 PM
 * @Description: TODO
 **/
@Component
public class OrderCancelDeclareExecutor {

    private final static Logger LOGGER = LoggerFactory.getLogger(OrderCancelDeclareExecutor.class);

    /**
     * 取消申报
     * 1、看是否支持取消 不支持报错
     * 2、支持则看订单状态以及其他情况处理
     * 3、成功取消申报则、清空申报信息
     * 4、不支持取消申报的 a. 物流不支持 b. 订单已出库等.
     *
     * @param companyId
     * @param order
     */
    public void cancleDeclare(Long companyId, OrderInfoDO order) {
        System.out.println("取消交运申报！");
    }

}
