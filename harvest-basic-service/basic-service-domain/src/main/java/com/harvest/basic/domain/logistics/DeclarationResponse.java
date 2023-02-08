package com.harvest.basic.domain.logistics;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Alodi
 * @Date: 2023/2/8 3:26 PM
 * @Description: 申报结果
 **/
@Data
public class DeclarationResponse implements Serializable {

    private static final long serialVersionUID = -419994436322699235L;

    @ApiModelProperty("是否申报成功")
    private Boolean success;

    @ApiModelProperty("运单号")
    private String deliveryNo;

    @ApiModelProperty("申报结果信息")
    private String message;

    @ApiModelProperty("申报时间")
    private String declareTime;

    @ApiModelProperty("面单信息")
    private DeclarationDataFile file;

    @ApiModelProperty("申报结果特性")
    private Object feature;

}
