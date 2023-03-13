package com.harvest.oms.repository.domain.tag;

import com.harvest.oms.repository.enums.tag.OrderTagSourceEnum;
import com.harvest.oms.repository.enums.tag.OrderTagStyleEnum;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: Alodi
 * @Date: 2023/2/7 8:24 PM
 * @Description: TODO
 **/
public class SystemOrderTag {

    private static final int START_VALUE = OrderTagSourceEnum.SYSTEM.getValue();

    private static final SystemOrderTag INSTANCE = new SystemOrderTag();

    public static final OrderTagDefinition VIP = build(
            1,
            "VIP",
            "重点客户",
            "重点客户",
            "#FFB91B"
    );

    public static final OrderTagDefinition BLACK_USER = build(
            2,
            "黑",
            "黑名单用户",
            "黑名单用户",
            "#000000"
    );


    private static OrderTagDefinition build(int value, String prefix, String hover, String desc, String color) {
        return new OrderTagDefinition(START_VALUE + value, prefix, hover, desc, color);
    }

    private static OrderTagDefinition build(int value, String prefix, String hover, String desc, String color, OrderTagStyleEnum orderTagStyleEnum) {
        return new OrderTagDefinition(START_VALUE + value, prefix, hover, desc, color, orderTagStyleEnum);
    }

    private static OrderTagDefinition build(int value, String prefix, String hover, String desc, String color, Class<?> clazz, OrderTagStyleEnum orderTagStyleEnum) {
        return new OrderTagDefinition(START_VALUE + value, prefix, hover, desc, color, clazz, orderTagStyleEnum);
    }

    /**
     * 根据 tagValue 获取描述
     *
     * @param tagValue
     * @return
     */
    public static OrderTagDefinition valueOf(int tagValue) {
        return all().stream().filter(tag -> tag.getTagValue() == tagValue).findAny().orElse(null);
    }

    public static Collection<OrderTagDefinition> all() {
        List<Field> fields = new ArrayList<>(Arrays.asList(INSTANCE.getClass().getDeclaredFields()));
        Class<?>[] innerClass = INSTANCE.getClass().getDeclaredClasses();
        if (innerClass.length > 0) {
            for (Class<?> clazz : innerClass) {
                fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            }
        }
        return fields.stream().filter(field -> field.getType() == OrderTagDefinition.class).map(filed -> {
            try {
                return (OrderTagDefinition) filed.get(INSTANCE);
            } catch (Exception e) {
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toSet());
    }

    public static void main(String[] args) {
        OrderTagDefinition orderTagDefinition = valueOf(10001);
        System.out.println(orderTagDefinition.toString());
        System.out.println("==============");
        Collection<OrderTagDefinition> all = all();
        all.forEach(System.out::println);
    }

}
