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

    @ApiModelProperty(value = "最小值", notes = "根据业务决定 左开/闭 右开/闭")
    protected T min;
    @ApiModelProperty(value = "最大值", notes = "根据业务决定 左开/闭 右开/闭")
    protected T max;

    public boolean isNull() {
        return Objects.isNull(min) && Objects.isNull(max);
    }

}
