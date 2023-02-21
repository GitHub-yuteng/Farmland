package com.harvest.rule.domain.logistics;

import com.harvest.core.rule.confine.RuleMatch;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: Alodi
 * @Date: 2023/2/21 7:43 PM
 * @Description: TODO
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class LogisticsRuleMatch extends RuleMatch {

    private static final long serialVersionUID = -8717954223149621923L;

    private Long logisticsId;

    private Long channelId;

}
