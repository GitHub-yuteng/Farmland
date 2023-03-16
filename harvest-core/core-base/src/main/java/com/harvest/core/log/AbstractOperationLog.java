package com.harvest.core.log;

import com.harvest.core.domain.CompanyId;
import com.harvest.core.enums.IEnum;
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

    @ApiModelProperty("表主键id")
    protected Long id;

    @ApiModelProperty("业务主键id eg:order_id")
    protected Long requestId;

    @ApiModelProperty("业务主键id eg:order_id")
    protected Long businessId;

    @ApiModelProperty("日志内容")
    protected String content;

    @ApiModelProperty("操作类型")
    protected OperationType operationType;

    @ApiModelProperty("日志备注信息")
    protected String remark;

    @ApiModelProperty("内部日志")
    protected boolean internal;

    @ApiModelProperty("异常日志")
    protected boolean exception;

    @ApiModelProperty("操作人")
    protected Long userId;

    @ApiModelProperty("日志记录时间")
    protected LocalDateTime logTime;

    @Getter
    @AllArgsConstructor
    public enum OperationType implements IEnum<Integer> {

        /**
         * 操作类型
         */
        MODIFY(1, "【修改】"),

        ;

        private final Integer type;

        private final String operation;

        /**
         * 获取枚举唯一值
         *
         * @return 唯一值
         */
        @Override
        public Integer getKey() {
            return this.type;
        }
    }
}
