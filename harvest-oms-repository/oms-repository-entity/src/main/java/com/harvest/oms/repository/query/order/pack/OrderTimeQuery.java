package com.harvest.oms.repository.query.order.pack;

import com.harvest.core.domain.range.DataTimeRange;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OrderTimeQuery {

    @ApiModelProperty("下单时间")
    private DataTimeRange createdTime;

    @ApiModelProperty("付款时间")
    private DataTimeRange paidTime;

    @ApiModelProperty("发货时间")
    private DataTimeRange sendTime;

    @ApiModelProperty("结束时间")
    private DataTimeRange endTime;

    @ApiModelProperty("发货截止时间")
    private DataTimeRange deliveryDeadlineTime;

    @ApiModelProperty("业务修改时间")
    private DataTimeRange modifyTime;

    @ApiModelProperty("打印时间")
    private DataTimeRange printTime;

    @ApiModelProperty("交运时间")
    private DataTimeRange declarationTime;

    @ApiModelProperty("订单审核时间")
    private DataTimeRange orderAuditTime;

    @ApiModelProperty("缺货统计时间")
    private DataTimeRange lackTime;

    @ApiModelProperty("记录落户创建时间 ｜ 如不是同步订单落单 则同 createdTime")
    private DataTimeRange rcTime;

    @ApiModelProperty("记录修改时间")
    private DataTimeRange rmTime;

}