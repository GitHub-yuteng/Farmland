package com.harvest.oms.repository.domain.order.base;

import com.harvest.core.context.ContextHolder;
import com.harvest.core.enums.log.OperationLogEnum;
import com.harvest.core.generator.IdGenerator;
import com.harvest.core.log.OperationLog;
import com.harvest.core.log.RecordLog;
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
public class OrderOperationLog extends RecordLog implements OperationLog {

    private static final long serialVersionUID = 4087359112058852127L;

    @Override
    public OperationLogEnum type() {
        return OperationLogEnum.ORDER;
    }

    private Long orderItemId;

    public static OrderOperationLog init() {
        OrderOperationLog log = new OrderOperationLog();
        log.setId(IdGenerator.generate());
        log.setUserId(ContextHolder.getContext().getUserId());
        log.setCompanyId(ContextHolder.getContext().getCompanyId());
        log.setRequestId(ContextHolder.getContext().getRequestId());
        log.setLogTime(LocalDateTime.now());
        log.setInternal(false);
        log.setException(false);
        return log;
    }

    public static OrderOperationLog build(Long businessId, OperationType operationType, String prefix, String content) {
        OrderOperationLog build = init();
        build.setBusinessId(businessId);
        build.setOperationType(operationType);
        build.setPrefix(prefix);
        build.setContent(content);
        return build;
    }

}
