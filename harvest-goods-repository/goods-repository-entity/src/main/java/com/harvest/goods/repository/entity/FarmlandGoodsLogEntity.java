package com.harvest.goods.repository.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 商品操作日志流水表
 * </p>
 *
 * @author Alodi
 * @since 2023-01-02 22:52:58
 */
@Getter
@Setter
@TableName("farmland_goods_log")
@ApiModel(value = "FarmlandGoodsLogEntity对象", description = "商品操作日志流水表")
public class FarmlandGoodsLogEntity {

    @ApiModelProperty("primary key")
    @TableId("id")
    private Long id;

    @ApiModelProperty("公司ID")
    @TableField("COMPANY_ID")
    private Long companyId;

    @ApiModelProperty("操作类型")
    @TableField("type")
    private Integer type;

    @ApiModelProperty("商品ID")
    @TableField("spu_id")
    private Long spuId;

    @ApiModelProperty("商品规格ID")
    @TableField("sku_id")
    private Long skuId;

    @ApiModelProperty("操作日志")
    @TableField("operation_log")
    private String operationLog;

    @TableField(value = "rc_time", fill = FieldFill.INSERT)
    private LocalDateTime rcTime;
}
