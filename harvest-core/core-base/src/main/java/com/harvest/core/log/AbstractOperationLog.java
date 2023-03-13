package com.harvest.core.log;

import com.harvest.core.domain.CompanyId;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * @Author: Alodi
 * @Date: 2023/3/13 4:01 PM
 * @Description: TODO
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class AbstractOperationLog extends CompanyId {

    private static final long serialVersionUID = 4035269531031643140L;

    protected Long id;

    protected Long userId;

    protected Long businessId;

    protected String content;

    @ApiModelProperty("日志记录时间")
    protected LocalDateTime logTime;

    @ApiModelProperty("操作类型")
    protected OperationType operationType;

    @ApiModelProperty("日志备注信息")
    private String remark;

    @ApiModelProperty("异常日志")
    private boolean exception;

    @Getter
    @AllArgsConstructor
    public enum OperationType {

        /**
         * 操作类型
         */
        MODIFY("【修改】"),

        ;

        private final String operation;

    }
}
