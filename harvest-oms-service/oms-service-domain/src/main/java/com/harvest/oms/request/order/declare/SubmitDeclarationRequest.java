package com.harvest.oms.request.order.declare;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.harvest.core.batch.BatchResultId;
import com.harvest.core.enums.logistics.LogisticsEnum;
import com.harvest.oms.domain.order.OrderInfoDO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Author: Alodi
 * @Date: 2023/2/5 4:13 PM
 * @Description: @JsonIgnore 标注为业务流转对象与前端无关
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class SubmitDeclarationRequest extends BatchResultId implements Serializable {

    private static final long serialVersionUID = -3113055454947802845L;

    @JsonIgnore
    @ApiModelProperty("订单信息-业务流转")
    private OrderInfoDO order;

    @JsonIgnore
    @ApiModelProperty("承运商类型-业务流转")
    private LogisticsEnum logistics;

}
