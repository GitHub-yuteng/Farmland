package com.harvest.oms.request.order.warehouse;

import com.harvest.core.domain.CompanyId;
import com.harvest.oms.domain.order.OrderInfoDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: Alodi
 * @Date: 2023/2/9 10:52 AM
 * @Description: TODO
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class SubmitWmsOrderMessage extends CompanyId {

    private static final long serialVersionUID = 8995582465766361901L;

    private OrderInfoDO order;
    
}
