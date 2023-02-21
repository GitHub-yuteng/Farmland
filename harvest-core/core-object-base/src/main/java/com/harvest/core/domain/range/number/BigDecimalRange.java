package com.harvest.core.domain.range.number;

import com.harvest.core.constants.GlobalMacroDefinition;
import com.harvest.core.domain.range.AbstractRange;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @Author: Alodi
 * @Date: 2022/12/18 4:31 PM
 * @Description: TODO
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class BigDecimalRange extends AbstractRange<BigDecimal> implements GlobalMacroDefinition {

    private static final long serialVersionUID = -6499146792370632729L;

    /**
     * 在范围之内
     *
     * @param value
     * @return
     */
    public boolean inScopeEq(BigDecimal value) {
        if (super.isNull()) {
            return true;
        }
        // 边界都存在   min <= value <= max
        if (Objects.nonNull(min) && Objects.nonNull(max)) {
            return value.compareTo(min) >= DEFAULT_0 && value.compareTo(max) <= DEFAULT_0;
        }

        // value <= max
        if (Objects.isNull(min) && Objects.nonNull(max)) {
            return value.compareTo(max) <= DEFAULT_0;
        }

        // value >= min
        if (Objects.nonNull(min) && Objects.isNull(max)) {
            return value.compareTo(min) >= DEFAULT_0;
        }
        return true;
    }

    public boolean inScope(BigDecimal value) {
        if (super.isNull()) {
            return true;
        }
        // 边界都存在   min <= value <= max
        if (Objects.nonNull(min) && Objects.nonNull(max)) {
            return value.compareTo(min) > DEFAULT_0 && value.compareTo(max) < DEFAULT_0;
        }

        // value <= max
        if (Objects.isNull(min) && Objects.nonNull(max)) {
            return value.compareTo(max) < DEFAULT_0;
        }

        // value >= min
        if (Objects.nonNull(min) && Objects.isNull(max)) {
            return value.compareTo(min) > DEFAULT_0;
        }
        return true;
    }

    public static void main(String[] args) {
        BigDecimalRange bigDecimalRange = new BigDecimalRange();
        bigDecimalRange.setMin(BigDecimal.valueOf(0));
        bigDecimalRange.setMax(BigDecimal.valueOf(10));

        System.out.println("=========inScopeEq=========");
        System.out.println(bigDecimalRange.inScopeEq(BigDecimal.ONE));
        System.out.println(bigDecimalRange.inScopeEq(BigDecimal.valueOf(10)));
        System.out.println(bigDecimalRange.inScopeEq(BigDecimal.valueOf(9)));
        System.out.println(bigDecimalRange.inScopeEq(BigDecimal.valueOf(11)));
        System.out.println(bigDecimalRange.inScopeEq(BigDecimal.valueOf(0)));
        System.out.println(bigDecimalRange.inScopeEq(BigDecimal.valueOf(-11)));

        System.out.println("=========inScope=========");
        System.out.println(bigDecimalRange.inScope(BigDecimal.ONE));
        System.out.println(bigDecimalRange.inScope(BigDecimal.valueOf(10)));
        System.out.println(bigDecimalRange.inScope(BigDecimal.valueOf(9)));
        System.out.println(bigDecimalRange.inScope(BigDecimal.valueOf(11)));
        System.out.println(bigDecimalRange.inScope(BigDecimal.valueOf(0)));
        System.out.println(bigDecimalRange.inScope(BigDecimal.valueOf(-11)));

//        =========inScopeEq=========
//        true
//        true
//        true
//        false
//        true
//        false
//        =========inScope=========
//        true
//        false
//        true
//        false
//        false
//        false
    }
}
