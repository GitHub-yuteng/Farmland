package com.harvest.oms.repository.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
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
 * @author yt
 */
@Configuration
@MapperScan(basePackages = {"com.harvest.oms.repository.mapper"}, sqlSessionFactoryRef = "omsSqlSessionFactory")
public class JdbcDataSource {

    public final static String OMS_TRANSACTION_MANAGER = "omsTransactionManager";
    public final static String OMS_TRANSACTION_TEMPLATE = "omsTransactionTemplate";

    private final static String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/farmland_oms";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "123456";

    @Bean(name = "oms_dataSource")
    public DataSource dataSource() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setName("node-oms-mysql");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl(JDBC_URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        dataSource.setInitialSize(5);
        dataSource.setMaxActive(20);
        dataSource.setMinIdle(5);
        dataSource.setTestWhileIdle(true);
        dataSource.setFilters("stat");
        dataSource.setMaxWait(60000);
        dataSource.setTimeBetweenEvictionRunsMillis(60000);
        dataSource.setMinEvictableIdleTimeMillis(300000);
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);
        dataSource.setPoolPreparedStatements(true);
        dataSource.setMaxOpenPreparedStatements(100);
        dataSource.setConnectionProperties("druid.stat.slowSqlMillis=5000");
        dataSource.setConnectionProperties("druid.stat.logSlowSql=true");
        return dataSource;
    }

    @Primary
    @Bean(name = "omsSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(this.sqlSessionFactory());
    }

    @Primary
    @Bean(name = "omsSqlSessionFactory")
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
    @Bean(name = JdbcDataSource.OMS_TRANSACTION_MANAGER)
    public DataSourceTransactionManager dataSourceTransactionManager() throws SQLException {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean(value = JdbcDataSource.OMS_TRANSACTION_TEMPLATE)
    public TransactionTemplate transactionTemplate() throws SQLException {
        TransactionTemplate transactionTemplate = new TransactionTemplate();
        transactionTemplate.setTransactionManager(dataSourceTransactionManager());
        //PROPAGATION_REQUIRED????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
        //PROPAGATION_NESTED???????????????????????????????????????????????????????????????????????????????????????????????????PROPAGATION_REQUIRED??????????????????
        //?????????PROPAGATION_NESTED????????????????????????????????????JDBC 3.0??????????????????????????????????????????????????????
        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        return transactionTemplate;
    }
}
