package com.harvest.oms.domain.order.declare;

import com.harvest.core.domain.CompanyId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: Alodi
 * @Date: 2023/2/5 4:20 PM
 * @Description: TODO
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderDeclarationDO extends CompanyId {

    private static final long serialVersionUID = 5207642073238121865L;

}
