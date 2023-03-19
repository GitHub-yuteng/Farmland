package com.harvest.oms.service.order.handler;

import com.harvest.core.constants.GlobalMacroDefinition;
import com.harvest.oms.repository.client.order.OrderWriteRepositoryClient;
import com.harvest.oms.service.order.event.OrderEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: Alodi
 * @Date: 2023/3/17 11:04 AM
 * @Description: TODO
 **/
public abstract class AbstractBizOrderHandler implements GlobalMacroDefinition {

    @Autowired
    protected OrderWriteRepositoryClient orderWriteRepositoryClient;

    @Autowired
    protected OrderEventPublisher orderEventPublisher;

    /**
     * 更新
     *
     * @return
     */
    protected abstract String update();

}
