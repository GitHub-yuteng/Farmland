package com.harvest.core.rule.confine;

import com.harvest.core.domain.CompanyId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: Alodi
 * @Date: 2023/2/21 11:07 AM
 * @Description: 完整规则(包含 各种细分规则) {@link com.harvest.core.rule.RuleSection}
 *
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class Rule extends CompanyId {

    private static final long serialVersionUID = 1191747191243574152L;


}
