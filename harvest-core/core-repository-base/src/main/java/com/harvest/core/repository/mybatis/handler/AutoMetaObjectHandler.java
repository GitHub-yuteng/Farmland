package com.harvest.core.repository.mybatis.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

/**
 * @Author: Alodi
 * @Date: 2023/2/25 1:51 PM
 * @Description: TODO
 **/
public class AutoMetaObjectHandler implements MetaObjectHandler {
    /**
     * 插入元对象字段填充（用于插入时对公共字段的填充）
     *
     * @param metaObject 元对象
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        this.fillStrategy(metaObject, "rcTime", LocalDateTime.now());
        metaObject.setValue("rmTime", LocalDateTime.now());
    }


    /**
     * 更新元对象字段填充（用于更新时对公共字段的填充）
     *
     * @param metaObject 元对象
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        metaObject.setValue("rmTime", LocalDateTime.now());
    }
}
