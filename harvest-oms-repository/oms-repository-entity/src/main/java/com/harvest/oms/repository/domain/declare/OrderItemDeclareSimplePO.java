package com.harvest.oms.repository.domain.declare;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author: Alodi
 * @Date: 2023/2/16 10:23 PM
 * @Description: TODO
 **/
@Data
public class OrderItemDeclareSimplePO implements Serializable {

    private static final long serialVersionUID = -1023164652734027451L;

    @TableId("order_item_id")
    private Long orderItemId;

    @TableField("COMPANY_ID")
    private Long companyId;

    @TableField("order_id")
    private Long orderId;

    @TableField("spu_id")
    private Long spuId;

    @TableField("sku_id")
    private Long skuId;

    @TableField("quantity")
    private BigDecimal quantity;
}
