package com.harvest.core.monitor.enums;

import com.harvest.core.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: Alodi
 * @Date: 2023/1/29 5:52 PM
 * @Description: TODO
 **/
@Getter
@AllArgsConstructor
public enum MonitorEventEnum implements IEnum<String> {

    /**
     * 监控事件
     */
    OMS_ORDER_QUERY("monitor.oms.order.query", "订单查询"),

    ;

    private final String event;
    private final String name;

    @Override
    public String getKey() {
        return this.event;
    }
}
