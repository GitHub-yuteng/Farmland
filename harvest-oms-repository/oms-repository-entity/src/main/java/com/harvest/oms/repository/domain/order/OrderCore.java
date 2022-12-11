package com.harvest.oms.repository.domain.order;

import com.harvest.oms.repository.domain.CompanyId;
import com.harvest.oms.repository.domain.order.pack.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: Alodi
 * @Date: 2022/12/10 8:56 PM
 * @Description: 订单核心领域模型
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderCore extends CompanyId {

    private static final long serialVersionUID = -2926021609995331681L;

    private Long orderId;

    @ApiModelProperty("订单号")
    private String orderNo;

    @ApiModelProperty("订单来源")
    private Integer orderSource;

    @ApiModelProperty("店铺id")
    private Long shopId;

    @ApiModelProperty("订单状态")
    private Integer orderStatus;

    @ApiModelProperty("订单时间")
    private OrderTime orderTime;

    @ApiModelProperty("订单时间")
    private OrderAddress orderAddress;

    @ApiModelProperty("订单标签")
    private OrderTag orderTag;

    @ApiModelProperty("订单金额")
    private OrderAmount orderAmount;

    @ApiModelProperty("订单备注")
    private OrderRemark orderRemark;
}
