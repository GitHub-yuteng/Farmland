package com.harvest.oms.repository.domain.order.pack;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author: Alodi
 * @Date: 2022/12/11 1:30 AM
 * @Description: TODO
 **/
@Data
public class OrderTime implements Serializable {

    private static final long serialVersionUID = 6352258267056322684L;

    @ApiModelProperty("下单时间")
    private LocalDateTime createdTime;

    @ApiModelProperty("支付时间")
    private LocalDateTime paidTime;

    @ApiModelProperty("发货时间")
    private LocalDateTime sendTime;

    @ApiModelProperty("发货截止时间")
    private LocalDateTime deliveryDeadlineTime;

    @ApiModelProperty("业务修改时间")
    private LocalDateTime modifyTime;

    @ApiModelProperty("打单时间")
    private LocalDateTime printTime;

    @ApiModelProperty("审核时间")
    private LocalDateTime auditTime;

    @ApiModelProperty("订单完成或结束时间")
    private LocalDateTime endTime;
}
