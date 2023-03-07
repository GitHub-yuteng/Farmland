package com.harvest.oms.repository.domain.order.base;

import com.harvest.oms.repository.enums.tag.OrderTagSourceEnum;
import com.harvest.oms.repository.enums.tag.OrderTagStyleEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: Alodi
 * @Date: 2022/12/11 1:30 AM
 * @Description: TODO
 **/
@Data
public class OrderTag implements Serializable {

    private static final long serialVersionUID = -4601884852299102212L;

    @ApiModelProperty("订单标记Id")
    private Long tagId;

    @ApiModelProperty("订单Id")
    private Long orderId;

    @ApiModelProperty("订单标记值")
    private Integer tagValue;

    @ApiModelProperty("订单标记来源")
    private OrderTagSourceEnum tagSource;

    @ApiModelProperty("根据业务是否继续执行判断点")
    private Boolean processed;

    @ApiModelProperty("订单标记扩展信息")
    private String simpleExtension;

    @ApiModelProperty("额外信息")
    private Object extension;

    @ApiModelProperty("样式")
    private Display display;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Display implements Serializable {

        private static final long serialVersionUID = -6998746840175300238L;

        @ApiModelProperty("显示字符 如 合、拆 等")
        private String prefix;

        @ApiModelProperty("鼠标悬停描述")
        private String hover;

        @ApiModelProperty("描述信息")
        private String description;

        @ApiModelProperty("颜色值")
        private String rgb;

        @ApiModelProperty("样式")
        private OrderTagStyleEnum style;
    }

}
