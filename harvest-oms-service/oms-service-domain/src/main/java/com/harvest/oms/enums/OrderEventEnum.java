package com.harvest.oms.enums;

/**
 * @Author: Alodi
 * @Date: 2022/12/12 3:41 PM
 * @Description: 订单事件枚举
 **/
public enum OrderEventEnum {

    /*订单创建*/
    CREATED,

    /*订单付款*/
    PAID,

    /*订单审核*/
    AUDIT,

    /*打回审核*/
    RETURN_AUDIT,

    /*订单发货*/
    DELIVERY,

    /*订单关闭、退款*/
    CLOSE,

    /*订单删除*/
    DELETE,

    /*订单恢复，对应订单删除*/
    RESTORE,

    /*修改仓库*/
    UPDATE_WAREHOUSE,

    /*商品信息变更*/
    GOODS_CHANGE,

    /*订单标签变更*/
    TAG_MODIFY,

    /*订单备注变更*/
    REMARK_MODIFY,

    /*订单合并*/
    MERGED
}
