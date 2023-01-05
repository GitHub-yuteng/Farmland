package com.harvest.core.repository.mybatis.handler;

import com.harvest.core.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.util.Assert;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author: Alodi
 * @Date: 2023/1/5 10:33 PM
 * @Description: TODO
 **/
@MappedJdbcTypes(JdbcType.VARCHAR)
public class ObjectJsonHandler implements TypeHandler<Object> {

    private final static String LIST_JSON_START = "[";

    private Class<?> clazz;

    public ObjectJsonHandler(Class<?> javaType) {
        this.clazz = javaType;
    }

    public ObjectJsonHandler() {
    }

    @Override
    public void setParameter(PreparedStatement statement, int i, Object parameter, JdbcType jdbcType) throws SQLException {
        if (parameter == null) {
            statement.setString(i, "");
            return;
        }
        statement.setString(i, JsonUtils.object2Json(parameter));
    }

    @Override
    public Object getResult(ResultSet resultSet, String columnName) throws SQLException {
        return getTarget(resultSet.getString(columnName));
    }

    @Override
    public Object getResult(ResultSet resultSet, int columnIndex) throws SQLException {
        return getTarget(resultSet.getString(columnIndex));
    }

    @Override
    public Object getResult(CallableStatement statement, int columnIndex) throws SQLException {
        return getTarget(statement.getString(columnIndex));
    }

    private Object getTarget(String json) {
        Assert.notNull(this.clazz, "请指定JavaType");
        try {
            if (StringUtils.isNotEmpty(json)) {
                if (json.startsWith(LIST_JSON_START)) {
                    return JsonUtils.json2ObjectList(json, clazz);
                } else {
                    return JsonUtils.json2Object(json, clazz);
                }
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }
}
