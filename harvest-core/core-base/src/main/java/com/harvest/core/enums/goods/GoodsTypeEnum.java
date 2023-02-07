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
public enum GoodsTypeEnum implements IEnum<Integer> {

    /**
     * 商品类型
     */
    GOODS_SINGLE    (1, "单规格商品"),
    GOODS_MULTIPLE  (2, "多规格商品"),

    ;

    public final Integer type;

    public final String value;

    @Override
    public Integer getKey() {
        return this.type;
    }
}
