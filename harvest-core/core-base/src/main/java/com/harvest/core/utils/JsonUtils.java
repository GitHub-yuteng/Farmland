/*
@(#)JacksonUtil.java   2021-07-12
 
Copyright (c) 2011-2021 杭州湖畔网络技术有限公司 
保留所有权利 
本软件为杭州湖畔网络技术有限公司所有及包含机密信息，须遵守其相关许可证条款进行使用。
Copyright (c) 2011-2021 HUPUN Network Technology CO.,LTD.
All rights reserved.
This software is the confidential and proprietary information of HUPUN
Network Technology CO.,LTD("Confidential Information").  You shall not
disclose such Confidential Information and shall use it only in
accordance with the terms of the license agreement you entered into with HUPUN.
Website：http://www.hupun.com
 */

package com.harvest.core.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.MapLikeType;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 使用Jackson处理json
 *
 * @author alodi
 */
public class JsonUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static final String OBJECT_JSON_START = "{";
    private static final String OBJECT_JSON_END = "}";

    private static final String ARRAY_JSON_START = "[";
    private static final String ARRAY_JSON_END = "]";


    static {
        //序列化时候统一日期格式
        OBJECT_MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        //设置null时候不序列化(只针对对象属性)
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //反序列化时，属性不存在的兼容处理
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //单引号处理
        OBJECT_MAPPER.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        //允许出现特殊字符和转义符
        OBJECT_MAPPER.configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true);
        //设置任何字段可见
        OBJECT_MAPPER.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        OBJECT_MAPPER.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.ANY);
        OBJECT_MAPPER.setVisibility(PropertyAccessor.IS_GETTER, JsonAutoDetect.Visibility.ANY);
        OBJECT_MAPPER.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Date.class, new JsonDeserializer<Date>() {
            @Override
            public Date deserialize(JsonParser p, DeserializationContext context) throws IOException {
                String text = p.getText();
                try {
                    return DateUtils.parse(text, DateUtils.DATE_TIME_FORMAT);
                } catch (Exception e) {
                    /*失败后尝试使用时间戳转换*/
                    try {
                        return new Date(Long.parseLong(text));
                    } catch (Exception e1) {
                        LOGGER.error("转换失败:{}", e.getMessage());
                    }
                }
                return null;
            }
        });
        OBJECT_MAPPER.registerModule(module);
    }

    /**
     * 简单判断字符串是否为json
     *
     * @param str 输入字符串
     * @return 是否为json字符串
     */
    public static boolean isJson(String str) {
        boolean result = false;
        if (!StringUtils.isEmpty(str)) {
            str = str.trim();
            if (str.startsWith(OBJECT_JSON_START) && str.endsWith(OBJECT_JSON_END)) {
                result = true;
            } else if (str.startsWith(ARRAY_JSON_START) && str.endsWith(ARRAY_JSON_END)) {
                result = true;
            }
        }
        return result;
    }

    /**
     * 将json字符串转成map
     *
     * @param jsonStr json字符串
     * @return map
     */
    public static Map<String, Object> json2Map(String jsonStr) {
        MapLikeType mapLikeType = OBJECT_MAPPER.getTypeFactory().constructMapLikeType(HashMap.class, String.class, Object.class);
        try {
            return OBJECT_MAPPER.readValue(jsonStr, mapLikeType);
        } catch (IOException e) {
            throw new RuntimeException("Json转换失败", e);
        }
    }

    /**
     * 字符串转对象
     *
     * @param json  字符串
     * @param clazz 目标对象类型
     * @param <T>   目标对象类型
     * @return 目标对象
     */
    public static <T> T json2Object(String json, Class<T> clazz) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        if (!isJson(json)) {
            return null;
        }
        try {
            /*先尝试原文解析*/
            return OBJECT_MAPPER.readValue(json, clazz);
        } catch (IOException e) {
            try {
                /*处理特殊字符后再解析*/
                json = StringEscapeUtils.unescapeJava(json);
                return OBJECT_MAPPER.readValue(json, clazz);
            } catch (IOException exception) {
                LOGGER.error("转换json出错，json内容: " + json);
                throw new RuntimeException("Json转换失败！", exception);
            }
        }
    }

    /**
     * json字符串转换对象集合
     *
     * @param json  json字符串
     * @param clazz 类型
     * @param <T>   泛型
     * @return 集合
     */
    public static <T> List<T> json2ObjectList(String json, Class<T> clazz) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        try {
            JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructCollectionType(ArrayList.class, clazz);
            return OBJECT_MAPPER.readValue(json, javaType);
        } catch (IOException e) {
            throw new RuntimeException("Json转换失败！", e);
        }
    }

    /**
     * json -> 对象
     * <p>	数据对象类型: new TypeReference<OuterClazz<InnerClazz>>
     */
    public static <T> T jsonToObj(String json, TypeReference<T> type) {
        try {
            return OBJECT_MAPPER.readValue(json, type);
        } catch (IOException e) {
            throw new RuntimeException("Json转换失败！", e);
        }
    }

    /**
     * 对象转json字符串
     *
     * @param entity 实例
     * @return json字符串
     */
    public static String object2Json(Object entity) {
        try {
            return OBJECT_MAPPER.writeValueAsString(entity);
        } catch (IOException e) {
            throw new RuntimeException("Json转换失败！", e);
        }
    }

}
