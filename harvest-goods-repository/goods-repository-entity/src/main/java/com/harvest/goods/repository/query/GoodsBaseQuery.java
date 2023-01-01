package com.harvest.goods.repository.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Alodi
 * @Date: 2022/12/31 4:25 PM
 * @Description: TODO
 **/
@Data
public class GoodsBaseQuery implements Serializable {

    private static final long serialVersionUID = -6094656862676274931L;

    @ApiModelProperty("商品Id")
    private Long spuId;

    @ApiModelProperty("商品规格Id")
    private Long skuId;
}
