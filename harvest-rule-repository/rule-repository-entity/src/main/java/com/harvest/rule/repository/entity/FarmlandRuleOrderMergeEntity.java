package com.harvest.rule.repository.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 订单合并规则
 * </p>
 *
 * @author Alodi
 * @since 2022-12-19 17:42:01
 */
@Getter
@Setter
@TableName("farmland_rule_order_merge")
@ApiModel(value = "FarmlandRuleOrderMergeEntity对象", description = "订单合并规则")
public class FarmlandRuleOrderMergeEntity {

    @TableId("id")
    private Long id;
}
