package com.harvest.rule.repository.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 订单拆单规则
 * </p>
 *
 * @author Alodi
 * @since 2022-12-18 21:08:40
 */
@Getter
@Setter
@TableName("farmland_rule_order_split")
@ApiModel(value = "FarmlandRuleOrderSplitEntity对象", description = "订单拆单规则")
public class FarmlandRuleOrderSplitEntity {

    @TableId("id")
    private Long id;
}
