package com.harvest.goods.repository.domain.goods.base;

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

    private BigDecimal purchasePrice;

    private BigDecimal wholesalePrice;

    private BigDecimal salePrice;

    private BigDecimal tagPrice;

}
