package com.harvest.oms.domain.order.log;

import com.harvest.core.enums.log.OperationLogEnum;
import com.harvest.core.log.OperationLog;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @Author: Alodi
 * @Date: 2023/3/13 3:16 PM
 * @Description: TODO
 **/
public class OrderOperationLog implements OperationLog {

    @Override
    public OperationLogEnum type() {
        return OperationLogEnum.ORDER;
    }

    private Long orderId;

    private String orderNo;

    private String content;

    @ApiModelProperty("日志记录时间")
    private Date logTime;

    @ApiModelProperty("异常日志")
    private boolean exception;

    @ApiModelProperty("日志备注信息")
    private String remark;
}
