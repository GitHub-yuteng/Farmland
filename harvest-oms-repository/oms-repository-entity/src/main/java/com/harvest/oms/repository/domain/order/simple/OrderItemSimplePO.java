package com.harvest.oms.repository.domain.order.simple;

import com.harvest.core.enums.oms.OrderStatusEnum;
import com.harvest.oms.repository.domain.order.base.OrderTime;
import com.harvest.oms.repository.domain.order.OrderCore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

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
    private OrderStatusEnum orderItemStatus;

    @ApiModelProperty("订单明细时间")
    private OrderTime orderItemTime;

    @ApiModelProperty("运单号")
    private String deliveryNo;

    @ApiModelProperty("商品Id")
    private Long spuId;

    @ApiModelProperty("商品编码")
    private String spuCode;

    @ApiModelProperty("商品名称")
    private String spuName;

    @ApiModelProperty("商品规格Id")
    private Long skuId;

    @ApiModelProperty("商品规格编码")
    private String skuCode;

    @ApiModelProperty("商品规格名称")
    private String skuName;

    @ApiModelProperty("单价")
    private BigDecimal unitPrice;

    @ApiModelProperty("折扣单价")
    private BigDecimal discountedPrice;

    @ApiModelProperty("是否组合商品")
    private Integer isPackage;

    @ApiModelProperty("数量")
    private BigDecimal quantity;

    @ApiModelProperty("金额大字段｜Json")
    private String amount;

    @ApiModelProperty("缺货数量")
    private BigDecimal stockLack;

    @ApiModelProperty("商品外部图片")
    private String pictureOssUrl;

}
