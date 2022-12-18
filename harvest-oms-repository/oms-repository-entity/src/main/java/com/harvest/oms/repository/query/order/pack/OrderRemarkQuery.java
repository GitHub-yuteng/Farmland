package com.harvest.oms.repository.query.order.pack;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Alodi
 * @Date: 2022/12/18 3:55 PM
 * @Description: TODO
 **/
@Data
public class OrderRemarkQuery {

    @ApiModelProperty("买家留言")
    private OrderRemark buyer;

    @ApiModelProperty("卖家备注")
    private OrderRemark seller;

    @ApiModelProperty("系统备注")
    private OrderRemark system;

    @Data
    public static class OrderRemark {

        @ApiModelProperty("false-有内容，true-无内容")
        private boolean empty;

        @ApiModelProperty("如果包含内容情况下，针对出入内容做模糊查询")
        private String remark;

        public boolean isEmpty() {
            return empty;
        }

        public void setEmpty(boolean empty) {
            this.empty = empty;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }
}
