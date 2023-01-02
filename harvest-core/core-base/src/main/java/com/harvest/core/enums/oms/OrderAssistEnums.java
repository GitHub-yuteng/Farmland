package com.harvest.core.enums.oms;

import com.harvest.core.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: Alodi
 * @Date: 2023/1/2 11:20 PM
 * @Description: 订单辅助信息枚举
 **/
public interface OrderAssistEnums {


    @Getter
    @AllArgsConstructor
    enum OrderTagRelationEnum implements IEnum<Integer> {

        /**
         * 订单级别
         */
        ORDER(1),

        /**
         * 订单级别
         */
        ORDER_ITEM(2);

        private final int key;

        @Override
        public Integer getKey() {
            return key;
        }

    }
}
