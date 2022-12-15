package com.harvest.core.domain.range;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * @Author: Alodi
 * @Date: 2022/12/12 10:19 PM
 * @Description: 范围抽象类
 **/
@Data
public abstract class AbstractRange<T> implements Serializable {

    private static final long serialVersionUID = 2927654076965095700L;

    @ApiModelProperty(value = "最小值", notes = "大于等于最小值，即左闭区间")
    private T min;
    @ApiModelProperty(value = "最大值", notes = "小于等于最大值，即右闭区间")
    private T max;

    public boolean isEmpty() {
        return Objects.isNull(min) && Objects.isNull(max);
    }

}
