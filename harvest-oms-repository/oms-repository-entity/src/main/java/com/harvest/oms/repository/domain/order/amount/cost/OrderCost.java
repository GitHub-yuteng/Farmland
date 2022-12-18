package com.harvest.oms.repository.domain.order.amount.cost;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: Alodi
 * @Date: 2022/12/11 1:27 AM
 * @Description: 订单成本
 **/
@Data
public class OrderCost {

    @ApiModelProperty(value = "配送成本", notes = "平台履约可能会直接扣除该费用，系统也支持商家手工录入")
    private BigDecimal deliveryCost;

    @ApiModelProperty(value = "卖家COD服务费成本", notes = "COD是货到付款的意思, COD全称为 Cash on delivery, 就是一手交钱一手交货的意思")
    private BigDecimal codCost;

    @ApiModelProperty(value = "订单佣金", notes = "订单成交的撮合费用，卖家向平台或第三方代理人/经纪人支付，每笔订单单独核算（因此广告等非订单粒度的不算佣金）")
    private OrderCommission orderCommission;

    @ApiModelProperty(value = "交易服务费", notes = "平台从订单金额里按比例扣除的一种，更多是针对交易的支付工具产生的手续费")
    private BigDecimal transactionServiceFee;

    @ApiModelProperty(value = "商品成本", notes = "由商家维护商品成本信息")
    private BigDecimal goodsCost;

    @ApiModelProperty(value = "卖家实际要缴纳的税额", notes = "部分平台履约可能会直接扣除该费用")
    private BigDecimal tax;

    @ApiModelProperty(value = "卖家优惠", notes = "卖家给买家补贴的金币、京豆等代币的成本")
    private BigDecimal sellerDiscount;

    @ApiModelProperty(value = "平台其他费用/杂费", notes = "平台需要卖家支付的各类费用，部分平台特有费用不在订单模型上一一列举。该项主要作为金额补平项")
    private BigDecimal sundryFee;
}