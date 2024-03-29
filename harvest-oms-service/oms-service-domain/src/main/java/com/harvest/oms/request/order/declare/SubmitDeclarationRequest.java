package com.harvest.oms.request.order.declare;

import com.harvest.core.batch.BatchId;
import com.harvest.core.enums.logistics.LogisticsEnum;
import com.harvest.oms.domain.logistics.LogisticsChannelAddressDO;
import com.harvest.oms.domain.order.OrderInfoDO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @Author: Alodi
 * @Date: 2023/2/5 4:13 PM
 * @Description:
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class SubmitDeclarationRequest extends BatchId {

    private static final long serialVersionUID = 8445956631463903676L;

    @ApiModelProperty("订单信息-业务流转")
    private OrderInfoDO order;

    @ApiModelProperty("物流类型-业务流转")
    private LogisticsEnum logisticsEnum;

    @ApiModelProperty("渠道地址")
    private List<LogisticsChannelAddressDO> channelAddressList;

    /**
     * {@link com.harvest.core.enums.logistics.auth.LogisticsAuth}
     */
    @ApiModelProperty(value = "对应物流授权", notes = "根据平台强转对应特性对象使用")
    private String authorization;

}
