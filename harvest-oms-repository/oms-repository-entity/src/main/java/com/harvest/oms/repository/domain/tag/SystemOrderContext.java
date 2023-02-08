package com.harvest.oms.repository.domain.tag;

import com.harvest.oms.repository.enums.tag.OrderTagSourceEnum;

/**
 * @Author: Alodi
 * @Date: 2023/2/7 8:24 PM
 * @Description: TODO
 **/
public class SystemOrderContext {

    private final static int START_VALUE = OrderTagSourceEnum.SYSTEM.getValue();

    public final static OrderTagDefinition VIP = build(
            1,
            "VIP",
            "重点客户",
            "重点客户",
            "#FFB91B"
    );

    public final static OrderTagDefinition BLACK_USER = build(
            2,
            "黑",
            "黑名单用户",
            "黑名单用户",
            "#000000"
    );


    private static OrderTagDefinition build(int value, String prefix, String hover, String desc, String color) {
        return new OrderTagDefinition(START_VALUE + value, prefix, hover, desc, color);
    }

}
