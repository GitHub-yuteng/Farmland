package com.harvest.oms.repository.domain.tag;

import com.harvest.oms.repository.enums.tag.OrderTagSourceEnum;

/**
 * @Author: Alodi
 * @Date: 2023/2/7 8:24 PM
 * @Description: TODO
 **/
public interface PlatformOrderTag {

    int START_VALUE = OrderTagSourceEnum.PLATFORM.getValue();

    class TikTok {
        
    }

}
