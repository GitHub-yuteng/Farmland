package com.harvest.core.enums.logistics;

import com.harvest.core.enums.logistics.auth.LogisticsAuth_Cainiao;
import com.harvest.core.enums.logistics.auth.LogisticsAuth_JD;
import com.harvest.core.enums.logistics.auth.LogisticsAuth_SF;
import com.harvest.core.enums.logistics.feature.LogisticsFeature_Cainiao;
import com.harvest.core.enums.logistics.feature.LogisticsFeature_JD;
import com.harvest.core.enums.logistics.feature.LogisticsFeature_SF;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: Alodi
 * @Date: 2023/2/4 2:42 PM
 * @Description: 不要格式化！
 **/
@Getter
@AllArgsConstructor
public enum LogisticsEnum {

    /**
     * 物流枚举
     */
    JD      (1,"JD",      "京东", "京东", LogisticsFeature_JD.class,       LogisticsAuth_JD.class),
    SF      (2,"SF",      "顺丰", "顺丰", LogisticsFeature_SF.class,       LogisticsAuth_SF.class),
    CAINIAO (3,"CAINIAO", "菜鸟", "菜鸟", LogisticsFeature_Cainiao.class,  LogisticsAuth_Cainiao.class),

    ;

    private final Integer type;

    private final String code;

    private final String name;

    private final String desc;

    private final Class<?> featureClass;

    private final Class<?> authorization;

    public static LogisticsEnum getEnumByCode(String code) {
        for (LogisticsEnum logisticsEnum : LogisticsEnum.values()) {
            if (logisticsEnum.getCode().equals(code)) {
                return logisticsEnum;
            }
        }
        return null;
    }

    public static LogisticsEnum getEnumByType(Integer type) {
        for (LogisticsEnum logisticsEnum : LogisticsEnum.values()) {
            if (logisticsEnum.getType().equals(type)) {
                return logisticsEnum;
            }
        }
        return null;
    }

    public static Class<?> getFeatureByType(Integer type) {
        for (LogisticsEnum logisticsEnum : LogisticsEnum.values()) {
            if (logisticsEnum.getType().equals(type)) {
                return logisticsEnum.getFeatureClass();
            }
        }
        return null;
    }

    public static Class<?> getAuthByType(Integer type) {
        for (LogisticsEnum logisticsEnum : LogisticsEnum.values()) {
            if (logisticsEnum.getType().equals(type)) {
                return logisticsEnum.getAuthorization();
            }
        }
        return null;
    }

}
