package com.harvest.rule.repository.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author Alodi
 * @since 2023-02-22 11:50:48
 */
@Getter
@Setter
@TableName("farmland_rule_logistics_match_smart")
@ApiModel(value = "FarmlandRuleLogisticsMatchSmartEntity对象", description = "")
public class FarmlandRuleLogisticsMatchSmartEntity {

    @TableId("id")
    private Long id;
}
