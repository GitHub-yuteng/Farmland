package com.harvest.rule.repository.domain.match.logistics;

import com.harvest.core.rule.confine.RuleCondition;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: Alodi
 * @Date: 2023/2/21 7:38 PM
 * @Description: TODO
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class LogisticsRuleCondition extends RuleCondition {

    private static final long serialVersionUID = -4335174394230994355L;

    @ApiModelProperty("国家编码")
    private String countryCode;
}
