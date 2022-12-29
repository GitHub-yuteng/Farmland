package com.harvest.core.repository.mybatis.handler;

import com.harvest.core.enums.IEnum;
import com.harvest.core.utils.EnumUtil;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author: Alodi
 * @Date: 2022/12/24 7:12 PM
 * @Description: TODO
 **/
public class EnumTypeHandler<E extends Enum<?> & IEnum<?>> extends BaseTypeHandler<E> {

    private Class<E> clazz;

    public EnumTypeHandler(Class<E> javaType) {
        this.clazz = javaType;
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, E parameter, JdbcType jdbcType) throws SQLException {
        Object key = parameter.getKey();
        if (key == null) {
            preparedStatement.setObject(i, null);
        }
        if (key instanceof Integer) {
            preparedStatement.setInt(i, (int) key);
        } else {
            preparedStatement.setString(i, String.valueOf(key));
        }
    }

    @Override
    public E getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        String result = resultSet.getString(columnName);
        return EnumUtil.convert(clazz, result);
    }

    @Override
    public E getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        String result = resultSet.getString(columnIndex);
        return EnumUtil.convert(clazz, result);
    }

    @Override
    public E getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        String result = callableStatement.getString(columnIndex);
        return EnumUtil.convert(clazz, result);
    }
}
