package com.harvest.oms.domain.order;

import com.harvest.basic.domain.shop.ShopDO;
import com.harvest.oms.domain.logistics.LogisticsChannelDO;
import com.harvest.oms.domain.order.declare.OrderDeclarationDO;
import com.harvest.oms.repository.domain.order.simple.OrderSimplePO;
import com.harvest.wms.repository.service.repository.domain.WarehouseDO;
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

    @ApiModelProperty("店铺信息")
    private ShopDO shop;

    @ApiModelProperty("订单明细")
    private Collection<OrderItemDO> orderItems;

    @ApiModelProperty("物流渠道信息")
    private LogisticsChannelDO logisticsChannel;

    @ApiModelProperty("订单交运申报信息")
    private OrderDeclarationDO declaration;

    /**
     * {@link com.harvest.oms.domain.order.feature.platform.PlatformFeature}
     */
    @ApiModelProperty(value = "平台特性", notes = "根据平台强转对应特性对象使用")
    private Object platformFeature;

    /**
     * {@link com.harvest.oms.domain.order.feature.company.CompanyFeature}
     */
    @ApiModelProperty(value = "公司特性", notes = "根据公司强转对应特性对象使用")
    private Object companyFeature;

    /**
     * {@link com.harvest.core.enums.logistics.feature.LogisticsFeature}
     */
    @ApiModelProperty(value = "物流特性", notes = "根据物流强转对应特性对象使用")
    private Object logisticsFeature;

    @ApiModelProperty("仓库信息")
    private WarehouseDO warehouse;
}
