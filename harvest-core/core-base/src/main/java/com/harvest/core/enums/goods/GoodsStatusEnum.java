package com.harvest.core.enums.goods;

import com.harvest.core.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: Alodi
 * @Date: 2022/12/30 4:52 PM
 * @Description: TODO
 **/
@Getter
@AllArgsConstructor
public enum GoodsStatusEnum implements IEnum<Integer> {

    /**
     * 状态枚举
     */
    ENABLE     (1, "启用"),
    DISABLE    (0, "停用"),
    DELETE     (-1, "删除");

    public final Integer status;

    public final String value;

    @Override
    public Integer getKey() {
        return this.status;
    }
}
