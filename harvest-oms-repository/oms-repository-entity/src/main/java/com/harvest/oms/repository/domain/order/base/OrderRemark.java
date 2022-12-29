package com.harvest.oms.repository.domain.order.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Alodi
 * @Date: 2022/12/11 1:30 AM
 * @Description: TODO
 **/
@Data
public class OrderRemark implements Serializable {

    private static final long serialVersionUID = -2059042170117558561L;

    @ApiModelProperty("订单Id")
    private Long orderId;

    @ApiModelProperty("卖家备注")
    private String sellerRemark;

    @ApiModelProperty("买家备注")
    private String buyerRemark;

    @ApiModelProperty("系统备注")
    private String systemRemark;

    @ApiModelProperty("需要打印备注")
    private String printRemark;
}
