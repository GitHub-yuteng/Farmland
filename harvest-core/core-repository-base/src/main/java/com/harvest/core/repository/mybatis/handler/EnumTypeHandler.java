package com.harvest.core.repository.mybatis.handler;

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
public class EnumTypeHandler<E> extends BaseTypeHandler<E> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, E e, JdbcType jdbcType) throws SQLException {

    }

    @Override
    public E getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        return null;
    }

    @Override
    public E getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public E getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        return null;
    }
}
