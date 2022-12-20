package com.harvest.rule.repository.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 物流匹配规则表
 * </p>
 *
 * @author Alodi
 * @since 2022-12-19 17:42:01
 */
@Getter
@Setter
@TableName("farmland_rule_delivery_match")
@ApiModel(value = "FarmlandRuleDeliveryMatchEntity对象", description = "物流匹配规则表")
public class FarmlandRuleDeliveryMatchEntity {

    @TableId("id")
    private Long id;
}
