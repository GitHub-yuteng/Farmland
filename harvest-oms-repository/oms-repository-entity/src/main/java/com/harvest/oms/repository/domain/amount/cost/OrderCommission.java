package com.harvest.oms.repository.domain.amount.cost;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author: Alodi
 * @Date: 2022/12/18 10:50 PM
 * @Description: 订单佣金
 **/
@Data
public class OrderCommission implements Serializable {

    private static final long serialVersionUID = -7386879932360317942L;

    @ApiModelProperty(value = "平台佣金", notes = "指常见由电商平台收取的交易佣金")
    private BigDecimal platform;
    @ApiModelProperty(value = "撮合佣金", notes = "指常见由平台提供的代理经纪人收取的佣金，如淘宝客等")
    private BigDecimal broker;
    @ApiModelProperty(value = "其他佣金", notes = "其他在卖家收款前，已在订单账单扣除的佣金")
    private BigDecimal other;

}
