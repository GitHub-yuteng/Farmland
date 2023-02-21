package com.harvest.core.rule.confine;

import com.harvest.core.domain.CompanyId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: Alodi
 * @Date: 2023/2/20 1:38 PM
 * @Description: TODO
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class RuleCondition extends CompanyId {

    private static final long serialVersionUID = -8071643891926009136L;

}
