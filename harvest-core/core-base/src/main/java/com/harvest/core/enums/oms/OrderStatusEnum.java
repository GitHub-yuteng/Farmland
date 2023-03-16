package com.harvest.core.enums.oms;

import com.harvest.core.enums.IEnum;
import com.harvest.core.exception.ExceptionCodes;
import com.harvest.core.exception.StandardRuntimeException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author: Alodi
 * @Date: 2022/12/11 1:31 AM
 * @Description: 其他模块也要使用订单状态枚举, 放在核心模块以防枚举冗余
 **/
@Getter
@AllArgsConstructor
public enum OrderStatusEnum implements IEnum<Integer> {

    /**
     * 订单状态
     */
    WAIT_PAY(1, "待付款", true) {
        @Override
        public List<OrderStatusEnum> back() {
            throw new StandardRuntimeException(ExceptionCodes.CORE_MODULE_ERROR, "最前置状态！");
        }

        @Override
        public List<OrderStatusEnum> next() {
            return Collections.singletonList(APPROVE);
        }
    },

    APPROVE(10, "待审核", false) {
        @Override
        public List<OrderStatusEnum> back() {
            throw new StandardRuntimeException(ExceptionCodes.CORE_MODULE_ERROR, "不可回退！");
        }

        @Override
        public List<OrderStatusEnum> next() {
            return Collections.unmodifiableList(Arrays.asList(FINANCE_APPROVE, ALLOCATE));
        }
    },

    FINANCE_APPROVE(20, "财务审核", false) {
        @Override
        public List<OrderStatusEnum> back() {
            return Collections.singletonList(APPROVE);
        }

        @Override
        public List<OrderStatusEnum> next() {
            return Collections.unmodifiableList(Arrays.asList(APPROVE, ALLOCATE, PRINT));
        }
    },

    WAIT_DECLARATION(25, "待交运", false) {
        @Override
        public List<OrderStatusEnum> back() {
            return Collections.unmodifiableList(Arrays.asList(APPROVE, FINANCE_APPROVE));
        }

        @Override
        public List<OrderStatusEnum> next() {
            return Collections.unmodifiableList(Arrays.asList(ALLOCATE, PRINT));
        }
    },

    ALLOCATE(27, "待分配", false) {
        @Override
        public List<OrderStatusEnum> back() {
            return Collections.unmodifiableList(Arrays.asList(APPROVE, FINANCE_APPROVE, WAIT_DECLARATION));
        }

        @Override
        public List<OrderStatusEnum> next() {
            return Collections.singletonList(PRINT);
        }
    },

    PRINT(30, "打单配货", true) {
        @Override
        public List<OrderStatusEnum> back() {
            return Collections.unmodifiableList(Arrays.asList(APPROVE, FINANCE_APPROVE, WAIT_DECLARATION, ALLOCATE));
        }

        @Override
        public List<OrderStatusEnum> next() {
            return Collections.singletonList(COLLECT);
        }
    },

    COLLECT(31, "待拣货", true) {
        @Override
        public List<OrderStatusEnum> back() {
            return Collections.unmodifiableList(Arrays.asList(APPROVE, FINANCE_APPROVE, WAIT_DECLARATION, ALLOCATE, PRINT));
        }

        @Override
        public List<OrderStatusEnum> next() {
            return Collections.singletonList(CHECK);
        }
    },

    CHECK(32, "验货", true) {
        @Override
        public List<OrderStatusEnum> back() {
            return Collections.unmodifiableList(Arrays.asList(APPROVE, FINANCE_APPROVE, WAIT_DECLARATION, ALLOCATE, PRINT, COLLECT));
        }

        @Override
        public List<OrderStatusEnum> next() {
            return Collections.singletonList(PACKAGE);
        }
    },

    PACKAGE(33, "打包", true) {
        @Override
        public List<OrderStatusEnum> back() {
            return Collections.unmodifiableList(Arrays.asList(APPROVE, FINANCE_APPROVE, WAIT_DECLARATION, ALLOCATE, PRINT, COLLECT, CHECK));
        }

        @Override
        public List<OrderStatusEnum> next() {
            return Collections.singletonList(WEIGH);
        }
    },

    WEIGH(34, "称重", true) {
        @Override
        public List<OrderStatusEnum> back() {
            return Collections.unmodifiableList(Arrays.asList(APPROVE, FINANCE_APPROVE, WAIT_DECLARATION, ALLOCATE, PRINT, COLLECT, CHECK, PACKAGE));
        }

        @Override
        public List<OrderStatusEnum> next() {
            return Collections.singletonList(WAIT_SHIP);
        }
    },

    WAIT_SHIP(40, "待发货", true) {
        @Override
        public List<OrderStatusEnum> back() {
            return Collections.unmodifiableList(Arrays.asList(APPROVE, FINANCE_APPROVE, WAIT_DECLARATION, ALLOCATE, PRINT, COLLECT, CHECK, PACKAGE, WEIGH));
        }

        @Override
        public List<OrderStatusEnum> next() {
            return Collections.unmodifiableList(Arrays.asList(PART_SHIP, SHIPPED));
        }
    },

    PART_SHIP(41, "部分发货", true) {
        @Override
        public List<OrderStatusEnum> back() {
            throw new StandardRuntimeException(ExceptionCodes.CORE_MODULE_ERROR, "状态不可改变！");
        }

        @Override
        public List<OrderStatusEnum> next() {
            return Collections.unmodifiableList(Arrays.asList(SHIPPED, FINISHED));
        }
    },

    SHIPPED(50, "已发货", true) {
        @Override
        public List<OrderStatusEnum> back() {
            throw new StandardRuntimeException(ExceptionCodes.CORE_MODULE_ERROR, "状态不可改变！");
        }

        @Override
        public List<OrderStatusEnum> next() {
            return Collections.unmodifiableList(Arrays.asList(FINISHED, CLOSED));
        }
    },

    FINISHED(90, "已完成", true) {
        @Override
        public List<OrderStatusEnum> back() {
            throw new StandardRuntimeException(ExceptionCodes.CORE_MODULE_ERROR, "状态不可改变！");
        }

        @Override
        public List<OrderStatusEnum> next() {
            throw new StandardRuntimeException(ExceptionCodes.CORE_MODULE_ERROR, "最终状态, 不可改变！");
        }
    },

    CLOSED(-1, "关闭/已退款", true) {
        @Override
        public List<OrderStatusEnum> back() {
            throw new StandardRuntimeException(ExceptionCodes.CORE_MODULE_ERROR, "状态不可改变！");
        }

        @Override
        public List<OrderStatusEnum> next() {
            throw new StandardRuntimeException(ExceptionCodes.CORE_MODULE_ERROR, "最终状态, 不可改变！");
        }
    };

    /**
     * 状态值
     */
    private final int status;

    /**
     * 描述
     */
    private final String value;

    /**
     * 是否为WMS状态
     */
    private final boolean wms;

    @Override
    public Integer getKey() {
        return this.status;
    }

    private static final Map<Integer, OrderStatusEnum> CACHE;

    static {
        CACHE = Arrays.stream(OrderStatusEnum.values()).collect(Collectors.toMap(OrderStatusEnum::getStatus, Function.identity()));
    }

    public static OrderStatusEnum getByStatus(Integer status) {
        return CACHE.get(status);
    }

    public abstract List<OrderStatusEnum> back();

    public abstract List<OrderStatusEnum> next();

    public boolean skip(OrderStatusEnum status) {
        return this.back().contains(status) || this.next().contains(status);
    }
}
