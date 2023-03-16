package com.harvest.inventory.repository.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("farmland_inventory_record_item")
@ApiModel(value = "FarmlandInventoryRecordItemEntity对象", description = "")
public class FarmlandInventoryRecordItemEntity {

    @TableId("id")
    private Long id;

    @TableField("COMPANY_ID")
    private Long companyId;

    @TableField("record_id")
    private Long recordId;
}
