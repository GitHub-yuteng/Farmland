package com.harvest.goods.repository.domain.goods.simple;

import com.harvest.core.enums.goods.GoodsStatusEnum;
import com.harvest.goods.repository.domain.goods.base.GoodsPrice;
import com.harvest.goods.repository.domain.goods.core.GoodsCore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * @Author: Alodi
 * @Date: 2022/12/30 4:03 PM
 * @Description: TODO
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class SkuSimplePO extends GoodsCore {

    private static final long serialVersionUID = -3884318162552549380L;

    @ApiModelProperty("商品规格Id")
    private Long skuId;

    @ApiModelProperty("商品规格编码")
    private String skuCode;

    @ApiModelProperty("商品规格名称")
    private String skuName;

    @ApiModelProperty("条码")
    private String barcode;

    @ApiModelProperty("商品规格金额")
    private GoodsPrice goodsPrice;

    @ApiModelProperty("采购周期")
    private BigDecimal procurementCycle;

    @ApiModelProperty("生产周期")
    private BigDecimal productionCycle;

    @ApiModelProperty("体积")
    private BigDecimal volume;

    @ApiModelProperty("最大体积")
    private BigDecimal maxVolume;

    @ApiModelProperty("长度")
    private BigDecimal length;

    @ApiModelProperty("宽度")
    private BigDecimal width;

    @ApiModelProperty("高度")
    private BigDecimal height;

    @ApiModelProperty("生产成本")
    private BigDecimal productionCost;

    @ApiModelProperty("关键字")
    private String keyWord;

    @ApiModelProperty("商品规格状态")
    private GoodsStatusEnum skuStatus;
}
