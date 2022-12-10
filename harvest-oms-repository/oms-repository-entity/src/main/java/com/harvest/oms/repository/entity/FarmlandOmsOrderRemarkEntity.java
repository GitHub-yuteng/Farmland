package com.harvest.oms.repository.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author Alodi
 * @since 2022-12-10 20:21:20
 */
@Getter
@Setter
@TableName("farmland_oms_order_remark")
public class FarmlandOmsOrderRemarkEntity {

    @TableId("id")
    private Long id;

    /**
     * 公司id
     */
    @TableField("COMPANY_ID")
    private Long companyId;

    /**
     * 0:订单｜1:订单明细
     */
    @TableField("record_type")
    private Integer recordType;

    /**
     * 记录id
     */
    @TableField("record_id")
    private Long recordId;

    /**
     * 卖家备注
     */
    @TableField("seller_remark")
    private String sellerRemark;

    /**
     * 买家备注
     */
    @TableField("buyer_remark")
    private String buyerRemark;

    /**
     * 系统备注
     */
    @TableField("system_remark")
    private String systemRemark;

    /**
     * 需要打印备注
     */
    @TableField("print_remark")
    private String printRemark;

    @TableField(value = "rc_time", fill = FieldFill.INSERT)
    private LocalDateTime rcTime;

    @TableField(value = "rm_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime rmTime;
}
