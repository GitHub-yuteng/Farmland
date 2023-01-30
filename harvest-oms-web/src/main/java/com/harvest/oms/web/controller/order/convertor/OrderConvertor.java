package com.harvest.oms.web.controller.order.convertor;

import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.vo.order.OrderInfoVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @Author: Alodi
 * @Date: 2023/1/30 5:36 PM
 * @Description: TODO
 **/
@Component
public class OrderConvertor {

    public Collection<OrderInfoVO> convert(Collection<OrderInfoDO> collection) {
        return collection.stream().map(orderInfoDO -> {
            OrderInfoVO orderInfoVO = new OrderInfoVO();
            BeanUtils.copyProperties(orderInfoDO, orderInfoVO);
            return orderInfoVO;
        }).collect(Collectors.toList());
    }
}
