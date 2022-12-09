package com.harvest.oms.service.order;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: Alodi
 * @Date: 2022/12/9 9:48 PM
 * @Description:
 **/
@FeignClient("harvest-oms-service")
public interface OrderReadService {

    @PostMapping(value = "getOrderInfo")
    String getOrderInfo(@RequestParam("orderId") long orderId);
}
