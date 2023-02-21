package com.harvest.core.domain.range.number;

import com.harvest.core.domain.range.AbstractRange;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Objects;

/**
 * @Author: Alodi
 * @Date: 2022/12/12 10:04 PM
 * @Description: TODO
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class LongRange extends AbstractRange<Long> implements Serializable {

    private static final long serialVersionUID = -6348328505906617029L;

    /**
     * 在范围之内
     *
     * @param value
     * @return
     */
    public boolean inScopeEq(Long value) {
        if (super.isNull()) {
            return true;
        }
        // 边界都存在   min <= value <= max
        if (Objects.nonNull(min) && Objects.nonNull(max)) {
            return value >= min && value <= max;
        }

        // value <= max
        if (Objects.isNull(min) && Objects.nonNull(max)) {
            return value <= max;
        }

        // value >= min
        if (Objects.nonNull(min) && Objects.isNull(max)) {
            return value >= min;
        }

        return false;
    }

    public boolean inScope(Long value) {
        if (super.isNull()) {
            return true;
        }

        // 边界都存在   min <= value <= max
        if (Objects.nonNull(min) && Objects.nonNull(max)) {
            return value > min && value < max;
        }

        // value <= max
        if (Objects.isNull(min) && Objects.nonNull(max)) {
            return value < max;
        }

        // value >= min
        if (Objects.nonNull(min) && Objects.isNull(max)) {
            return value > min;
        }

        return false;

    }
}
