package com.harvest.oms.repository.domain.order.update.remark;

import com.harvest.oms.repository.domain.order.base.OrderRemark;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: Alodi
 * @Date: 2023/3/17 10:27 AM
 * @Description: TODO
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderUpdateRemark extends OrderRemark {

    private static final long serialVersionUID = 3428363735277816338L;

    private Boolean append;

    private RemarkEnum remarkEnum;
}
