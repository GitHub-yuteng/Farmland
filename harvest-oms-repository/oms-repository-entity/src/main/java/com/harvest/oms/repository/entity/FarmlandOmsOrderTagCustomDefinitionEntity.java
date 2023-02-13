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
 * @since 2023-02-13 15:05:22
 */
@Getter
@Setter
@TableName("farmland_oms_order_tag_custom_definition")
@ApiModel(value = "FarmlandOmsOrderTagCustomDefinitionEntity对象", description = "")
public class FarmlandOmsOrderTagCustomDefinitionEntity {

    @TableId("id")
    private Long id;
}
