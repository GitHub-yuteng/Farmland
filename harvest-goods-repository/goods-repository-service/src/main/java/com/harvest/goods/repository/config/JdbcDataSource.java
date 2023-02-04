package com.harvest.goods.repository.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @Author: Alodi
 * @Date: 2023/1/10 10:43 PM
 * @Description:
 **/
@Configuration
@MapperScan(basePackages = {"com.harvest.goods.repository.mapper"}, sqlSessionFactoryRef = "goodsSqlSessionFactory")
public class JdbcDataSource {

    public final static String GOODS_TRANSACTION_MANAGER = "goodsTransactionManager";
    public final static String GOODS_TRANSACTION_TEMPLATE = "goodsTransactionTemplate";

    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Bean(name = "goods_dataSource")
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setName(dataSourceProperties.getName());
        dataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
        dataSource.setUrl(dataSourceProperties.getUrl());
        dataSource.setUsername(dataSourceProperties.getUsername());
        dataSource.setPassword(dataSourceProperties.getPassword());
        return dataSource;
    }

    @Primary
    @Bean(name = "goodsSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(this.sqlSessionFactory());
    }

    @Primary
    @Bean(name = "goodsSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(dataSource());

        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setCallSettersOnNulls(true);
        configuration.setMapUnderscoreToCamelCase(true);
        bean.setConfiguration(configuration);

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        bean.setMapperLocations(resolver.getResources("classpath*:/mapper/**/*.xml"));
        return bean.getObject();
    }

    @Primary
    @Bean(name = JdbcDataSource.GOODS_TRANSACTION_MANAGER)
    public DataSourceTransactionManager dataSourceTransactionManager() throws SQLException {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean(value = JdbcDataSource.GOODS_TRANSACTION_TEMPLATE)
    public TransactionTemplate transactionTemplate() throws SQLException {
        TransactionTemplate transactionTemplate = new TransactionTemplate();
        transactionTemplate.setTransactionManager(this.dataSourceTransactionManager());
        //PROPAGATION_REQUIRED：如果当前没有事务，就新建一个事务，如果已经存在一个事务中，加入到这个事务中。这是最常见的选择。
        //PROPAGATION_NESTED：如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则执行与PROPAGATION_REQUIRED类似的操作。
        //当使用PROPAGATION_NESTED时，底层的数据源必须基于JDBC 3.0，并且实现者需要支持保存点事务机制。
        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        return transactionTemplate;
    }
}
