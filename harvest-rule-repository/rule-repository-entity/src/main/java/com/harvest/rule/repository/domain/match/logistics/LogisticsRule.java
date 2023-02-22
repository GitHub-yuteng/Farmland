package com.harvest.rule.repository.domain.match.logistics;


import com.harvest.core.rule.confine.Rule;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: Alodi
 * @Date: 2023/2/20 6:05 PM
 * @Description: TODO
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class LogisticsRule extends Rule {

    private static final long serialVersionUID = 6338996064157768730L;

    public static final String RULE = "[物流匹配]";

    private String ruleName;

    private Integer priority;

    private Long logisticsId;

    private Long channelId;

    private Boolean isDefault;

    private Boolean status;

    @ApiModelProperty("规则")
    private LogisticsRuleSection ruleSection;

}
