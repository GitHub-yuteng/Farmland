package com.harvest.core.rule;

import java.io.Serializable;

/**
 * @Author: Alodi
 * @Date: 2023/2/20 10:06 AM
 * @Description: 规则接口
 **/
public interface RuleSection extends Serializable {

    /**
     * 优先级
     *
     * @return
     */
    default int priority() {
        return 0;
    }

    /**
     * 规则名称
     *
     * @return
     */
    String name();

}
