package com.harvest.oms.domain.order.goods;

import com.harvest.goods.repository.domain.goods.simple.GoodsSimplePO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: Alodi
 * @Date: 2023/2/9 11:02 AM
 * @Description: TODO
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderGoodsDO extends GoodsSimplePO {

    private static final long serialVersionUID = 3232857207960105994L;

    private OrderSkuDO sku;
}
