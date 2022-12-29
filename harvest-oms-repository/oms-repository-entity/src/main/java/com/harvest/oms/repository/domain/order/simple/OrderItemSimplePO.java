package com.harvest.oms.repository.domain.order.simple;

import com.harvest.oms.repository.domain.order.core.OrderCore;
import com.harvest.oms.repository.domain.order.base.OrderTime;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Author: Alodi
 * @Date: 2022/12/29 2:29 PM
 * @Description: TODO
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderItemSimplePO extends OrderCore implements Serializable {

    private static final long serialVersionUID = 7066605485429856710L;

    @ApiModelProperty("订单明细Id")
    private Long orderItemId;

    @ApiModelProperty("订单明细状态")
    private Integer orderItemStatus;

    @ApiModelProperty("订单明细时间")
    private OrderTime orderItemTime;

}
