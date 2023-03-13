package com.harvest.oms.domain.order.log;

import com.harvest.core.context.ContextHolder;
import com.harvest.core.enums.log.OperationLogEnum;
import com.harvest.core.generator.IdGenerator;
import com.harvest.core.log.AbstractOperationLog;
import com.harvest.core.log.OperationLog;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @Author: Alodi
 * @Date: 2023/3/13 3:16 PM
 * @Description: TODO
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrderOperationLog extends AbstractOperationLog implements OperationLog {

    private static final long serialVersionUID = 4087359112058852127L;

    @Override
    public OperationLogEnum type() {
        return OperationLogEnum.ORDER;
    }

    private String orderNo;

    public static OrderOperationLog init() {
        OrderOperationLog log = new OrderOperationLog();
        log.setId(IdGenerator.generate());
        log.setUserId(ContextHolder.getContext().getUserId());
        log.setLogTime(LocalDateTime.now());
        return log;
    }

}
