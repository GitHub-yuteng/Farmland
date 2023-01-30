package com.harvest.goods.repository.domain.goods.simple;

import com.harvest.goods.repository.domain.goods.GoodsCore;
import com.harvest.goods.repository.domain.goods.base.GoodsBusiness;
import com.harvest.goods.repository.domain.goods.base.GoodsSwitch;
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

    @ApiModelProperty("商品分类Id")
    private Long categoryId;
    @ApiModelProperty("商品Id")
    private String category;

    @ApiModelProperty("商品品牌Id")
    private Long brandId;
    @ApiModelProperty("商品品牌")
    private String brand;

    @ApiModelProperty("商品单位Id")
    private Long unitId;
    @ApiModelProperty("商品单位")
    private String unit;

    @ApiModelProperty("货号/产品编号")
    private String productNo;

    @ApiModelProperty("商品业务属性字段")
    private GoodsBusiness goodsBusiness;

    @ApiModelProperty("商品管理开关")
    private GoodsSwitch goodsSwitch;


}
