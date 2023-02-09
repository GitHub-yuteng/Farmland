package com.harvest.oms.domain.order.goods;

import com.harvest.goods.repository.domain.goods.simple.SkuSimplePO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: Alodi
 * @Date: 2023/2/9 11:02 AM
 * @Description: TODO
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderSkuDO extends SkuSimplePO {

    private static final long serialVersionUID = 7007903034164442547L;

}
