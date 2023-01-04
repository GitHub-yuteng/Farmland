package com.harvest.goods.repository.domain.goods.simple;

import com.baomidou.mybatisplus.annotation.TableField;
import com.harvest.goods.repository.domain.goods.core.GoodsCore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;

/**
 * @Author: Alodi
 * @Date: 2022/12/30 3:59 PM
 * @Description: 商品简要
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class GoodsSimplePO extends GoodsCore {

    private static final long serialVersionUID = 277894423587943036L;

    @ApiModelProperty("商品规格简要")
    private Collection<SkuSimplePO> skuSimples;

    @ApiModelProperty("货号/产品编号")
    private String productNo;

    @ApiModelProperty("长度单位")
    private Integer lengthUnit;

    @ApiModelProperty("宽度单位")
    private Integer weightUnit;

    @ApiModelProperty("体积单位")
    private Integer volumeUnit;

    @ApiModelProperty("保质期")
    private Integer qualityPeriod;

    @ApiModelProperty("禁收期")
    private Integer forbidReceivePeriod;

    @ApiModelProperty("禁售期")
    private Integer forbidSalePeriod;

    @ApiModelProperty("允收期")
    private Integer allowAcceptPeriod;

    @ApiModelProperty("生命周期(天)")
    private Integer lifeCycle;

}
