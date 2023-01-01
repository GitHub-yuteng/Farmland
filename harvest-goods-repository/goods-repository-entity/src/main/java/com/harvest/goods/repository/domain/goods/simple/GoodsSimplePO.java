package com.harvest.goods.repository.domain.goods.simple;

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

    @ApiModelProperty("商品货号")
    private String productNo;

}
