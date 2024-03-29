package com.harvest.oms.repository.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 订单标签
 * </p>
 *
 * @author Alodi
 * @since 2023-02-13 15:05:22
 */
@Getter
@Setter
@TableName("farmland_oms_order_tag")
@ApiModel(value = "FarmlandOmsOrderTagEntity对象", description = "订单标签")
public class FarmlandOmsOrderTagEntity {

    @ApiModelProperty("primary key")
    @TableId("id")
    private Long id;

    @ApiModelProperty("公司ID")
    @TableField("COMPANY_ID")
    private Long companyId;

    @ApiModelProperty("记录ID")
    @TableField("record_id")
    private Long recordId;

    @ApiModelProperty("1:订单｜2:订单明细")
    @TableField("record_type")
    private Integer recordType;

    @ApiModelProperty("eg:10001")
    @TableField("tag_value")
    private Integer tagValue;

    @ApiModelProperty("根据业务是否继续执行判断点")
    @TableField("processed")
    private Boolean processed;

    @ApiModelProperty("简单扩展｜添加索引")
    @TableField("simple_extension")
    private String simpleExtension;

    @ApiModelProperty("大字段扩展字段")
    @TableField("extension")
    private String extension;

    @ApiModelProperty("逻辑删除标记")
    @TableField("is_deleted")
    @TableLogic
    private Boolean isDeleted;

    @ApiModelProperty("记录创建时间")
    @TableField(value = "rc_time", fill = FieldFill.INSERT)
    private LocalDateTime rcTime;

    @ApiModelProperty("记录修改时间")
    @TableField(value = "rm_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime rmTime;
}
