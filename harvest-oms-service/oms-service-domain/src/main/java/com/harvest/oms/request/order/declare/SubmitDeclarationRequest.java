package com.harvest.oms.request.order.declare;

import com.harvest.core.batch.BatchId;
import com.harvest.core.enums.logistics.LogisticsEnum;
import com.harvest.oms.domain.logistics.LogisticsChannelAddressDO;
import com.harvest.oms.domain.order.OrderInfoDO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: Alodi
 * @Date: 2023/2/5 4:13 PM
 * @Description:
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class SubmitDeclarationRequest extends BatchId implements Serializable {

    private static final long serialVersionUID = -3113055454947802845L;

    @ApiModelProperty("订单信息-业务流转")
    private OrderInfoDO order;

    @ApiModelProperty("物流类型-业务流转")
    private LogisticsEnum logisticsType;

    @ApiModelProperty("渠道地址-业务流转")
    private List<LogisticsChannelAddressDO> channelAddressList;

    @ApiModelProperty("对应物流特征偏好-业务流转")
    private Object feature;

}
