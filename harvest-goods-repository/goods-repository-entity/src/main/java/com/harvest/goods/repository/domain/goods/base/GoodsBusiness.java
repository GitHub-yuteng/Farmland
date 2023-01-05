package com.harvest.goods.repository.domain.goods.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Alodi
 * @Date: 2023/1/5 8:24 PM
 * @Description: 商品业务属性字段
 **/
@Data
public class GoodsBusiness implements Serializable {

    private static final long serialVersionUID = -5710203958523672385L;

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
