package com.harvest.inventory.repository.entity;

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
 * @since 2023-03-16 21:20:20
 */
@Getter
@Setter
@TableName("farmland_inventory_record")
@ApiModel(value = "FarmlandInventoryRecordEntity对象", description = "")
public class FarmlandInventoryRecordEntity {

    @TableId("id")
    private Long id;
}
