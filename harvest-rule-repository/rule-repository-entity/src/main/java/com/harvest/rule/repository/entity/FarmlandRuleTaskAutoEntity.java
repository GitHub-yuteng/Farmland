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
 * @since 2022-12-19 17:42:01
 */
@Getter
@Setter
@TableName("farmland_rule_task_auto")
@ApiModel(value = "FarmlandRuleTaskAutoEntity对象", description = "")
public class FarmlandRuleTaskAutoEntity {

    @TableId("id")
    private Long id;
}
