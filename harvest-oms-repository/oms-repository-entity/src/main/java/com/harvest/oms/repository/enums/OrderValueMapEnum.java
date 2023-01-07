package com.harvest.oms.repository.enums;

import com.harvest.core.enums.IEnum;
import com.harvest.oms.repository.enums.value.OrderExtFile;
import com.harvest.oms.repository.enums.value.OrderLogisticsFile;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: Alodi
 * @Date: 2023/1/7 9:39 PM
 * @Description: 订单业务属性信息
 **/
@Getter
@AllArgsConstructor
public enum OrderValueMapEnum implements IEnum<Integer> {

    /**
     * 订单业务属性信息
     */
    ORDER_EXT_FILE         (1, "订单扩展文件", OrderExtFile.class),
    ORDER_LOGISTICS_FILE   (2, "订单扩展文件",OrderLogisticsFile .class);


    private final int type;

    private final String describe;

    private final Class<?> clazz;

    @Override
    public Integer getKey() {
        return this.type;
    }
}
