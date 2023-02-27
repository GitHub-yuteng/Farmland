package com.harvest.oms.repository.domain.declare;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: Alodi
 * @Date: 2023/2/16 10:23 PM
 * @Description: TODO
 **/
@Data
public class OrderDeclareSimplePO implements Serializable {

    private static final long serialVersionUID = -9000653848376469888L;

    @TableId("order_id")
    private Long orderId;

    @TableField("COMPANY_ID")
    private Long companyId;

    @TableField("status")
    private Integer status;

    private List<OrderItemDeclareSimplePO> items;

}
