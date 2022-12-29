package com.harvest.oms.repository.query.order;

import com.harvest.core.domain.query.PageQuery;
import com.harvest.oms.repository.query.order.pack.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Collection;

/**
 * @Author: Alodi
 * @Date: 2022/12/14 8:13 PM
 * @Description: 分页查询条件
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class PageOrderConditionQuery extends PageQuery implements Serializable {

    private static final long serialVersionUID = 1413821848778222159L;

    @ApiModelProperty("订单Ids")
    private Collection<Long> orderIds;

    @ApiModelProperty("订单号")
    private String orderNo;

    @ApiModelProperty("店铺ID集")
    private Collection<String> shopIds;

    @ApiModelProperty("订单状态")
    private Collection<Integer> statuses;

    @ApiModelProperty("包含标签")
    private Collection<Integer> includeTags;

    @ApiModelProperty("不包含标签")
    private Collection<Integer> excludeTags;

    @ApiModelProperty("订单时间相关条件")
    private OrderTimeQuery orderTime;

    @ApiModelProperty("订单备注相关条件")
    private OrderRemarkQuery orderRemark;

    @ApiModelProperty("订单商品种类相关条件")
    private OrderKindQuery orderKind;

    @ApiModelProperty("订单物流相关条件")
    private OrderDeliveryQuery orderDelivery;

    @ApiModelProperty("订单编号查询条件")
    private OrderNoQuery nos;

    @ApiModelProperty("是否是异常订单 0否 1是")
    private Boolean abnormal;
}
