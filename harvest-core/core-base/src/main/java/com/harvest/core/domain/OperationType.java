package com.harvest.core.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * @Author: Alodi
 * @Date: 2023/1/11 11:45 AM
 * @Description: 操作类型 一般为业务明细使用
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class OperationType extends CompanyId {

    private static final long serialVersionUID = -2196487939432085697L;

    @Getter
    public enum OperationEnum {
        /**
         * 操作类型
         */
        UNDO,
        NEW,
        MODIFIED,
        DELETED
    }

    private OperationEnum state$ = OperationEnum.UNDO;

}
