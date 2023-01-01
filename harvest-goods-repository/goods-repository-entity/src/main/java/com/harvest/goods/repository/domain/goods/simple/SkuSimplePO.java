package com.harvest.goods.repository.domain.goods.simple;

import com.harvest.core.enums.goods.GoodsStatusEnum;
import com.harvest.goods.repository.domain.goods.base.GoodsPrice;
import com.harvest.goods.repository.domain.goods.core.GoodsCore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

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

    @ApiModelProperty("商品规格状态")
    private GoodsStatusEnum skuStatus;

    @ApiModelProperty("商品规格金额")
    private GoodsPrice goodsPrice;
}
