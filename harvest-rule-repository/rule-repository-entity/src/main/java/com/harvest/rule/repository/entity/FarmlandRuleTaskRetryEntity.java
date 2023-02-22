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
 * @since 2023-02-22 11:50:49
 */
@Getter
@Setter
@TableName("farmland_rule_task_retry")
@ApiModel(value = "FarmlandRuleTaskRetryEntity对象", description = "")
public class FarmlandRuleTaskRetryEntity {

    @TableId("id")
    private Long id;
}
