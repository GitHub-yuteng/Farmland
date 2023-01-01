package com.harvest.goods.domain;

import com.harvest.goods.repository.domain.goods.simple.GoodsSimplePO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;

/**
 * @Author: Alodi
 * @Date: 2022/12/30 11:35 AM
 * @Description: TODO
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class GoodsInfoDO extends GoodsSimplePO {

    private static final long serialVersionUID = 2033398028122991696L;

    @ApiModelProperty("商品规格")
    private Collection<SkuInfoDO> skus;
}
