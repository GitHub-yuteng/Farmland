package com.harvest.oms.repository.domain.order.update;

import com.harvest.core.domain.CompanyId;
import com.harvest.oms.repository.domain.order.update.remark.OrderUpdateRemark;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @Author: Alodi
 * @Date: 2023/3/17 10:15 AM
 * @Description: TODO
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderSubmitUpdateField extends CompanyId {

    private static final long serialVersionUID = -9122915338044673521L;

    @ApiModelProperty("订单Id")
    private List<Long> orderIds;

    @ApiModelProperty("更新类型")
    private UpdateEnum updateEnum;

    @ApiModelProperty("更新仓库Id")
    private Long warehouseId;

    @ApiModelProperty("更新备注")
    private OrderUpdateRemark remark;

    /**
     * 更新
     */
    public enum UpdateEnum {

        /**
         * 更新类型
         */
        WAREHOUSE, LOGISTICS, REMARK,

        /**
         * 匹配更新
         */
        MATCH_WAREHOUSE, MATCH_LOGISTICS, MATCH_GOODS_GIFT
    }

}
