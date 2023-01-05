package com.harvest.goods.repository.domain.goods.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author: Alodi
 * @Date: 2022/12/30 4:41 PM
 * @Description: TODO
 **/
@Data
public class GoodsPrice implements Serializable {

    private static final long serialVersionUID = 8783710053887298282L;

    @ApiModelProperty("进价")
    private BigDecimal purchasePrice;

    @ApiModelProperty("批发价")
    private BigDecimal wholesalePrice;

    @ApiModelProperty("售价")
    private BigDecimal salePrice;

    @ApiModelProperty("吊牌价")
    private BigDecimal tagPrice;

}
