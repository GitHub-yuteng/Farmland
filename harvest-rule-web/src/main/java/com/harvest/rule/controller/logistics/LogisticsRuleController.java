package com.harvest.rule.controller.logistics;

import com.harvest.core.context.ContextHolder;
import com.harvest.core.domain.ResponseResult;
import com.harvest.core.path.HarvestRulePath;
import com.harvest.rule.client.logistics.LogisticsRuleClient;
import com.harvest.rule.repository.domain.match.logistics.LogisticsRule;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * @Author: Alodi
 * @Date: 2023/2/22 10:14 PM
 * @Description: TODO
 **/
@Api(value = "物流规则", tags = "物流规则")
@RestController
@RequestMapping(value = HarvestRulePath.LogisticsRule.LOGISTICS_RULE)
public class LogisticsRuleController {

    @Autowired
    private LogisticsRuleClient logisticsRuleClient;

    @ApiOperation("查询物流匹配规则")
    @PostMapping("/list")
    public ResponseResult<Collection<LogisticsRule>> listLogisticsRule() {
        Long companyId = ContextHolder.getContext().getCompanyId();
        Collection<LogisticsRule> logisticsRules = logisticsRuleClient.listLogisticsRule(companyId);
        return ResponseResult.success(logisticsRules);
    }

}
