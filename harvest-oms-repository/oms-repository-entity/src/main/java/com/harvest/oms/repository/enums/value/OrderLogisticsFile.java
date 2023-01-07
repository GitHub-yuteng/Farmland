package com.harvest.oms.repository.enums.value;

import com.harvest.core.domain.file.HarvestFile;
import com.harvest.oms.repository.enums.OrderValueMapEnum;
import lombok.Data;

import java.util.List;

/**
 * @Author: Alodi
 * @Date: 2023/1/7 10:51 PM
 * @Description: 订单物流信息文件
 **/
@Data
public class OrderLogisticsFile implements IOrderValueMap {

    private static final long serialVersionUID = -5586524607014030675L;

    private List<HarvestFile> files;

    @Override
    public OrderValueMapEnum getType() {
        return OrderValueMapEnum.ORDER_EXT_FILE;
    }
}