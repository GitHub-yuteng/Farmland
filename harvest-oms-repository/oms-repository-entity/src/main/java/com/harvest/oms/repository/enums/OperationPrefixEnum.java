package com.harvest.oms.repository.enums;

import com.harvest.core.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: Alodi
 * @Date: 2023/3/20 3:09 PM
 * @Description: TODO
 **/
@Getter
@AllArgsConstructor
public enum OperationPrefixEnum implements IEnum<Integer> {

    /**
     * 操作类型
     */
    AUDIT       (1, "订单审核"),

    ABNORMAL    (20, "提交异常"),

    REMARK      (21, "更新订单备注"),

    ;


    private final Integer type;
    private final String prefix;

    @Override
    public Integer getKey() {
        return this.type;
    }
}
