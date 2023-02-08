package com.harvest.oms.repository.domain.tag;

import com.harvest.oms.repository.enums.tag.OrderTagDisplayEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Alodi
 * @Date: 2023/2/7 8:33 PM
 * @Description: TODO
 **/
@Data
public class OrderTagDefinition {

    @ApiModelProperty("标签值")
    private int tagValue;

    @ApiModelProperty("显示字符 如 合、拆 等")
    private String prefix;

    @ApiModelProperty("鼠标悬停描述")
    private String hover;

    @ApiModelProperty("描述信息")
    private String description;

    @ApiModelProperty("颜色值")
    private String rgb;

    @ApiModelProperty("扩展记录信息")
    private Class<?> extensionClass;

    @ApiModelProperty("样式")
    private OrderTagDisplayEnum display;

    public OrderTagDefinition(int tagValue, String prefix, String hover, String description, String rgb) {
        this.tagValue = tagValue;
        this.prefix = prefix;
        this.hover = hover;
        this.description = description;
        this.rgb = rgb;
    }

    public OrderTagDefinition(int tagValue, String prefix, String hover, String description, String rgb, Class<?> extensionClass) {
        this.tagValue = tagValue;
        this.prefix = prefix;
        this.hover = hover;
        this.description = description;
        this.rgb = rgb;
        this.extensionClass = extensionClass;
    }
}
