package com.harvest.rule.controller.warehouse;

import com.harvest.core.path.HarvestRulePath;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Alodi
 * @Date: 2023/2/22 10:14 PM
 * @Description: TODO
 **/
@Api(value = "匹配仓库规则", tags = "仓库规则")
@RestController
@RequestMapping(value = HarvestRulePath.LogisticsRule.LOGISTICS_RULE)
public class WarehouseRuleController {


}
