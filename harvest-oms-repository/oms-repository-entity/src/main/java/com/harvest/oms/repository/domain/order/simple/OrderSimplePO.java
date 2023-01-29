package com.harvest.oms.repository.domain.order.simple;

import com.harvest.core.enums.oms.OrderStatusEnum;
import com.harvest.oms.repository.domain.amount.OrderAmount;
import com.harvest.oms.repository.domain.order.OrderCore;
import com.harvest.oms.repository.domain.order.base.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @Author: Alodi
 * @Date: 2022/12/11 1:25 AM
 * @Description: 订单信息简要 ｜ 一次查询, 查询出该订单所有简要信息（数据库信息）
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderSimplePO extends OrderCore {

    private static final long serialVersionUID = 2547095981099704006L;

    @ApiModelProperty("订单号")
    private String orderNo;

    @ApiModelProperty("承运商Id")
    private Long carrierId;

    @ApiModelProperty("渠道Id")
    private Long channelId;

    @ApiModelProperty("运单号")
    private String deliveryNo;

    @ApiModelProperty("订单状态")
    private OrderStatusEnum orderStatus;

    @ApiModelProperty("订单时间")
    private OrderTime orderTime;

    @ApiModelProperty("订单地址")
    private OrderAddress orderAddress;

    @ApiModelProperty("订单金额")
    private OrderAmount orderAmount;

    @ApiModelProperty("订单备注")
    private OrderRemark orderRemark;

    @ApiModelProperty("订单标签")
    private List<OrderTag> orderTags;

    @ApiModelProperty("订单仓库")
    private OrderWarehouse orderWarehouse;

}
