package com.harvest.oms.repository.domain.order.pack;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Author: Alodi
 * @Date: 2022/12/11 1:30 AM
 * @Description: TODO
 **/
public class OrderRemark implements Serializable {

    private static final long serialVersionUID = -2059042170117558561L;

    @ApiModelProperty("卖家备注")
    @TableField("seller_remark")
    private String sellerRemark;

    @ApiModelProperty("买家备注")
    @TableField("buyer_remark")
    private String buyerRemark;

    @ApiModelProperty("系统备注")
    @TableField("system_remark")
    private String systemRemark;

    @ApiModelProperty("需要打印备注")
    @TableField("print_remark")
    private String printRemark;
}
