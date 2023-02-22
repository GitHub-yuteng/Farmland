package com.harvest.rule.repository.client;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.harvest.core.annotation.feign.HarvestService;
import com.harvest.core.exception.ExceptionCodes;
import com.harvest.core.exception.StandardRuntimeException;
import com.harvest.core.rule.RuleSection;
import com.harvest.core.utils.JsonUtils;
import com.harvest.rule.repository.constants.HarvestRuleRepositoryApplications;
import com.harvest.rule.repository.domain.match.logistics.LogisticsRule;
import com.harvest.rule.repository.domain.match.logistics.LogisticsRuleSection;
import com.harvest.rule.repository.entity.FarmlandRuleLogisticsMatchEntity;
import com.harvest.rule.repository.entity.FarmlandRuleLogisticsMatchSectionEntity;
import com.harvest.rule.repository.enums.logistics.LogisticsRuleMatchEnum;
import com.harvest.rule.repository.mapper.FarmlandRuleLogisticsMatchMapper;
import com.harvest.rule.repository.mapper.FarmlandRuleLogisticsMatchSectionMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: Alodi
 * @Date: 2023/2/22 9:36 PM
 * @Description: TODO
 **/
@HarvestService(path = HarvestRuleRepositoryApplications.LogisticsPath.LOGISTICS_RULE)
public class LogisticsRuleRepositoryClientImpl implements LogisticsRuleRepositoryClient {

    private final static Logger LOGGER = LoggerFactory.getLogger(LogisticsRuleRepositoryClientImpl.class);

    @Autowired
    private FarmlandRuleLogisticsMatchMapper farmlandRuleLogisticsMatchMapper;

    @Autowired
    private FarmlandRuleLogisticsMatchSectionMapper farmlandRuleLogisticsMatchSectionMapper;

    @Override
    public Collection<LogisticsRule> listLogisticsRule(Long companyId) {
        List<FarmlandRuleLogisticsMatchEntity> ruleMatchs = farmlandRuleLogisticsMatchMapper.selectList(
                new QueryWrapper<FarmlandRuleLogisticsMatchEntity>().lambda().eq(FarmlandRuleLogisticsMatchEntity::getCompanyId, companyId)
        );
        if (CollectionUtils.isEmpty(ruleMatchs)) {
            return Collections.emptyList();
        }
        List<Long> ruleIds = ruleMatchs.stream().map(FarmlandRuleLogisticsMatchEntity::getId).distinct().collect(Collectors.toList());
        List<FarmlandRuleLogisticsMatchSectionEntity> ruleMatchSections = farmlandRuleLogisticsMatchSectionMapper.selectList(
                new QueryWrapper<FarmlandRuleLogisticsMatchSectionEntity>().lambda().in(FarmlandRuleLogisticsMatchSectionEntity::getRuleId, ruleIds)
        );

        Map<Long, List<FarmlandRuleLogisticsMatchSectionEntity>> sectionMap = ruleMatchSections.stream()
                .collect(Collectors.groupingBy(FarmlandRuleLogisticsMatchSectionEntity::getRuleId));

        return ruleMatchs.stream().map(ruleMatch -> {
            LogisticsRule logisticsRule = new LogisticsRule();
            logisticsRule.setRuleName(ruleMatch.getRuleName());
            logisticsRule.setPriority(ruleMatch.getPriority());
            logisticsRule.setLogisticsId(ruleMatch.getLogisticsId());
            logisticsRule.setChannelId(ruleMatch.getChannelId());
            logisticsRule.setIsDefault(ruleMatch.getIsDefault());
            logisticsRule.setStatus(ruleMatch.getStatus());
            logisticsRule.setId(ruleMatch.getId());
            logisticsRule.setCompanyId(companyId);
            List<FarmlandRuleLogisticsMatchSectionEntity> sections = sectionMap.get(ruleMatch.getId());
            if (CollectionUtils.isEmpty(sections)) {
                return logisticsRule;
            }
            LogisticsRuleSection ruleSection = new LogisticsRuleSection();
            logisticsRule.setRuleSection(ruleSection);

            sections.forEach(section -> {
                Integer sectionType = section.getSectionType();
                Class<? extends RuleSection> clazz = LogisticsRuleMatchEnum.getEnumByType(sectionType).getClazz();
                RuleSection content = JsonUtils.json2Object(section.getRuleContent(), clazz);
                assert content != null;
                Field field = Arrays.stream(ruleSection.getClass().getDeclaredFields())
                        .filter(item -> RuleSection.class.isAssignableFrom(item.getType()) && clazz.equals(item.getType()))
                        .findFirst()
                        .orElseThrow(() -> new StandardRuntimeException(ExceptionCodes.RULE_MODULE_ERROR, "未找到物流规则对应的属性字段!"));
                field.setAccessible(true);
                try {
                    field.set(section, ruleSection);
                } catch (IllegalAccessException e) {
                    LOGGER.error("设置物流规则内容发生异常", e);
                }

            });
            return logisticsRule;
        }).collect(Collectors.toList());
    }

}
