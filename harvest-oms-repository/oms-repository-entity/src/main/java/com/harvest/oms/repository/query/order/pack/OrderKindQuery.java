package com.harvest.oms.repository.query.order.pack;

import com.harvest.core.domain.range.number.BigDecimalRange;
import com.harvest.core.domain.range.number.IntegerRange;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Alodi
 * @Date: 2022/12/18 4:26 PM
 * @Description:
 **/
@Data
public class OrderKindQuery implements Serializable {

    private static final long serialVersionUID = 5254879368590995034L;

    @ApiModelProperty("商品种类查询范围")
    private IntegerRange spuKind;

    @ApiModelProperty("商品规格查询范围")
    private IntegerRange skuKind;

    @ApiModelProperty("商品数量查询范围")
    private BigDecimalRange quantity;

}
