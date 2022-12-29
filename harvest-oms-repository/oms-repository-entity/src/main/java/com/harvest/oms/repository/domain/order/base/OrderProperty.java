package com.harvest.oms.repository.domain.order.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author: Alodi
 * @Date: 2022/12/29 11:47 AM
 * @Description: TODO
 **/
@Data
public class OrderProperty implements Serializable {

    private static final long serialVersionUID = -1386527158618191565L;

    @ApiModelProperty("订单重量 固定以克为单位")
    private BigDecimal weight = BigDecimal.ZERO;

    @ApiModelProperty("订单体积 固定以立方分米为单位")
    private BigDecimal volume = BigDecimal.ZERO;
}
