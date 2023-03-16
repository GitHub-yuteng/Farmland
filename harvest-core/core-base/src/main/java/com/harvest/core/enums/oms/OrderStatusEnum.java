package com.harvest.core.enums.oms;

import com.harvest.core.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author: Alodi
 * @Date: 2022/12/11 1:31 AM
 * @Description: 其他模块也要使用订单状态枚举, 放在核心模块以防枚举冗余
 **/
@Getter
@AllArgsConstructor
public enum OrderStatusEnum implements IEnum<Integer> {

    /**
     * 订单状态
     */
    WAIT_PAY        (1, "待付款", true),

    APPROVE         (10, "待审核", false),

    FINANCE_APPROVE (20, "财务审核", false),

    ALLOCATE       (21, "待分配", false),

    PRINT           (30, "打单配货", true),

    COLLECT         (31, "待拣货", true),

    CHECK           (32, "验货", true),

    PACKAGE         (33, "打包", true),

    WEIGH           (34, "称重", true),

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

    private static final Map<Integer,OrderStatusEnum> CACHE;

    static {
        CACHE = Arrays.stream(OrderStatusEnum.values()).collect(Collectors.toMap(OrderStatusEnum::getStatus, Function.identity()));
    }

    public static OrderStatusEnum getByStatus(Integer status) {
        return CACHE.get(status);
    }
}
