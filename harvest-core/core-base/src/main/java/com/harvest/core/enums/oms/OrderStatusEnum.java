package com.harvest.core.enums.oms;

import com.harvest.core.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: Alodi
 * @Date: 2022/12/11 1:31 AM
 * @Description: 其他模块也要使用订单状态枚举，所以放在核心模块，以防枚举冗余
 **/
@Getter
@AllArgsConstructor
public enum OrderStatusEnum implements IEnum<Integer> {

    /**
     * 订单状态
     */
    WAIT_PAY        (1, "待付款", true),

    APPROVE         (10, "审核", false),

    FINANCE_APPROVE (20, "财务审核", false),

    ALLOCATED       (21, "待分配", false),

    PRINT           (30, "配货中", false),

    COLLECT         (31, "待拣货", false),

    CHECK           (32, "验货", false),

    PACKAGE         (33, "打包", false),

    WEIGH           (34, "称重", false),

    WAIT_SHIP       (40, "待发货", true),

    PART_SHIP       (41, "部分发货", true),

    SHIPPED         (50, "已发货", true),

    FINISHED        (90, "已完成", true),

    CLOSED          (-1, "关闭/已退款", true);

    /**
     * 状态值
     */
    private final int status;

    /**
     * 描述
     */
    private final String value;

    /**
     * 是否为WMS状态
     */
    private final boolean wms;

    @Override
    public Integer getKey() {
        return this.status;
    }
}
