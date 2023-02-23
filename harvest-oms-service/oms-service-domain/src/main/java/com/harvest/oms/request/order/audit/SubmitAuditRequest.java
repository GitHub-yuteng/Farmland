package com.harvest.oms.request.order.audit;

import com.harvest.core.batch.BatchId;
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
public class SubmitAuditRequest extends BatchId implements Serializable {

    private static final long serialVersionUID = -3113055454947802845L;

    @ApiModelProperty("订单信息-业务流转")
    private OrderInfoDO order;

    @ApiModelProperty("强制审核")
    private boolean force;

    public static class SubmitAuditItemRequest {


    }
}
