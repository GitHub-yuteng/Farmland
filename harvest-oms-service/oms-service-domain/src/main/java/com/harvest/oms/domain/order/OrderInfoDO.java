package com.harvest.oms.domain.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.harvest.oms.domain.order.logistics.OrderLogisticsChannelDO;
import com.harvest.oms.domain.order.platform.OrderPlatformFeature;
import com.harvest.oms.domain.order.platform.PlatformFeature;
import com.harvest.oms.repository.domain.order.simple.OrderSimplePO;
import com.harvest.wms.repository.repository.domain.WarehouseDO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;

/**
 * @Author: Alodi
 * @Date: 2022/12/11 1:25 AM
 * @Description: 订单信息领域模型 ｜ 包含其他业务领域模型
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderInfoDO extends OrderSimplePO {

    private static final long serialVersionUID = 2547095981099704006L;

    @ApiModelProperty("订单明细")
    private Collection<OrderItemDO> orderItems;

    @ApiModelProperty("物流渠道信息")
    private OrderLogisticsChannelDO logisticsChannel;

    @JsonIgnore
    @ApiModelProperty(value = "平台特性", notes = "根据平台强转对应特性对象使用")
    private OrderPlatformFeature<? extends PlatformFeature> platformFeature;

    @ApiModelProperty("仓库信息")
    private WarehouseDO warehouse;
}
