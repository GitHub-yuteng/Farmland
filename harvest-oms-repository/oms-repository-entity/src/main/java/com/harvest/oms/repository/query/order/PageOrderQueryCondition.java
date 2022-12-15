package com.harvest.oms.repository.query.order;

import com.harvest.core.domain.range.DataTimeRange;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Collection;

/**
 * @Author: Alodi
 * @Date: 2022/12/14 8:13 PM
 * @Description: TODO
 **/
@Data
public class PageOrderQueryCondition implements Serializable {

    private static final long serialVersionUID = 1413821848778222159L;

    @ApiModelProperty("店铺ID集")
    private Collection<String> shopIds;

    @ApiModelProperty("订单状态")
    private Collection<Integer> statuses;

    @ApiModelProperty("标签值")
    private Collection<Integer> tags;

    public static class OrderTimeQuery {

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

        @ApiModelProperty("记录创建时间 ｜ 如不是同步订单落单 则同 createdTime")
        private DataTimeRange rcTime;

        @ApiModelProperty("记录修改时间")
        private DataTimeRange rmTime;

        @ApiModelProperty("交运时间")
        private DataTimeRange handoverTime;

        @ApiModelProperty("订单审核时间")
        private DataTimeRange orderAuditTime;

        @ApiModelProperty("缺货统计时间")
        private DataTimeRange lackTime;

    }
}
