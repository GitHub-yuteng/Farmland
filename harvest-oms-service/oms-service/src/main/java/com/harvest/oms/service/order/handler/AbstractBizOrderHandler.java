package com.harvest.oms.service.order.handler;

import com.harvest.core.constants.GlobalMacroDefinition;
import com.harvest.oms.repository.client.order.OrderWriteRepositoryClient;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: Alodi
 * @Date: 2023/3/17 11:04 AM
 * @Description: TODO
 **/
public abstract class AbstractBizOrderHandler implements GlobalMacroDefinition {

    @Autowired
    protected OrderWriteRepositoryClient orderWriteRepositoryClient;

    /**
     * 更新
     *
     * @return
     */
    protected abstract String update();

}
