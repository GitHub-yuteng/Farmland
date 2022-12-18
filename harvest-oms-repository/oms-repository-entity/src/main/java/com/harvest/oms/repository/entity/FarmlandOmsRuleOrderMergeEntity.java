package com.harvest.oms.repository.entity;

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
 * @since 2022-12-18 20:23:34
 */
@Getter
@Setter
@TableName("farmland_oms_rule_order_merge")
@ApiModel(value = "FarmlandOmsRuleOrderMergeEntity对象", description = "")
public class FarmlandOmsRuleOrderMergeEntity {

    @TableId("id")
    private Long id;
}
