package com.harvest.goods.repository.domain.goods.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Alodi
 * @Date: 2023/1/5 8:15 PM
 * @Description: TODO
 **/
@Data
public class GoodsSwitch implements Serializable {

    private static final long serialVersionUID = 6201307532099022653L;

    @ApiModelProperty("批次管理")
    private Boolean openBatch;

    @ApiModelProperty("效期管理")
    private Boolean openValidity;

}
