package com.harvest.rule.repository.enums.logistics;

import com.harvest.core.enums.IEnum;
import com.harvest.core.exception.ExceptionCodes;
import com.harvest.core.exception.StandardRuntimeException;
import com.harvest.core.rule.RuleSection;
import com.harvest.rule.repository.domain.match.logistics.section.WarehouseRuleSection;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * @Author: Alodi
 * @Date: 2023/2/21 6:29 PM
 * @Description: 物流匹配
 **/
@Getter
@AllArgsConstructor
public enum LogisticsRuleMatchEnum implements IEnum<Integer> {

    /**
     * 物流匹配细分规则
     */
    WAREHOUSE_RULE(8, "仓库规则", WarehouseRuleSection.class),

    ;

    private final Integer type;

    private final String description;

    private final Class<? extends RuleSection> clazz;

    /**
     * 获取枚举唯一值
     *
     * @return 唯一值
     */
    @Override
    public Integer getKey() {
        return this.type;
    }

    public static LogisticsRuleMatchEnum valueOf(Class<? extends RuleSection> clazz) {
        return Arrays.stream(LogisticsRuleMatchEnum.values())
                .filter(item -> item.getClazz().equals(clazz))
                .findFirst()
                .orElseThrow(() -> new StandardRuntimeException(ExceptionCodes.RULE_MODULE_ERROR, "未找到对应物流规则枚举"));
    }
}
