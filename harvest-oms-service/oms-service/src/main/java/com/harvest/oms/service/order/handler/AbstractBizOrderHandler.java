package com.harvest.oms.service.order.handler;

import com.harvest.core.constants.GlobalMacroDefinition;

/**
 * @Author: Alodi
 * @Date: 2023/3/17 11:04 AM
 * @Description: TODO
 **/
public abstract class AbstractBizOrderHandler implements GlobalMacroDefinition {

    /**
     * 更新
     *
     * @return
     */
    protected abstract String update();

}
