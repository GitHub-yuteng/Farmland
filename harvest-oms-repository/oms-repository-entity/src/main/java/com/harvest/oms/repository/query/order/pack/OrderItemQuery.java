package com.harvest.oms.repository.query.order.pack;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Collection;

/**
 * @Author: Alodi
 * @Date: 2022/12/18 3:58 PM
 * @Description: 订单明细商品查询
 **/
@Data
public class OrderItemQuery {

    @ApiModelProperty("true-包含，false-不包含")
    private boolean contain;

    @ApiModelProperty("商品信息")
    private Collection<OrderItemGoods> itemGoods;

    @Data
    public static class OrderItemGoods {

        @ApiModelProperty("spuId")
        private String spuId;

        @ApiModelProperty("skuId")
        private String skuId;
    }
}
