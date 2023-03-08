package com.harvest.oms.repository.enums.declare;

import com.harvest.core.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: Alodi
 * @Date: 2023/2/27 11:14 PM
 * @Description: TODO
 **/
@Getter
@AllArgsConstructor
public enum DeclareStatusEnum implements IEnum<Integer> {

    /**
     * 交运状态
     */
    INIT                (0, "草稿交运信息"),
    WAIT_DECLARED       (1, "已提交交运信息"),
    SUCCESS             (9, "交运成功"),
    FAILURE             (-1,"交运失败")

    ;

    public final Integer type;

    public final String value;

    /**
     * 获取枚举唯一值
     *
     * @return 唯一值
     */
    @Override
    public Integer getKey() {
        return this.type;
    }
}
