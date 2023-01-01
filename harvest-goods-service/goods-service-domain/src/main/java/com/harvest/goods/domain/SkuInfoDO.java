package com.harvest.goods.domain;

import com.harvest.goods.repository.domain.goods.simple.SkuSimplePO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: Alodi
 * @Date: 2022/12/30 4:03 PM
 * @Description: TODO
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class SkuInfoDO extends SkuSimplePO {

    private static final long serialVersionUID = -4286623601822779913L;

}
