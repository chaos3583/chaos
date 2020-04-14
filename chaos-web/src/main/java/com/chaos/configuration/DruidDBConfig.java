//package com.chaos.configuration;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.context.annotation.PropertySource;
//
//import javax.sql.DataSource;
//import java.sql.SQLException;
//
///**
// * 设置连接信息；
// *
// * Created by Shawearn on 2017/2/6.
// */
////@Configuration
//public class DruidDBConfig {
//    private final static Logger logger = LoggerFactory.getLogger(DruidDBConfig.class);
//    @Value("${spring.datasource.url}")
//    private String dbUrl;
//    @Value("${spring.datasource.username}")
//    private String username;
//    @Value("${spring.datasource.password}")
//    private String password;
//    @Value("${spring.datasource.driverClassName}")
//    private String driverClassName;
//
//    @Bean     //声明其为Bean实例
////    @Primary  //在同样的DataSource中，首先使用被标注的DataSource
//    @ConfigurationProperties(prefix ="jdbc")
//    public DataSource dataSource() {
//        DruidDataSource datasource = new DruidDataSource();
//
//        datasource.setUrl(this.dbUrl);
//        datasource.setUsername(username);
//        datasource.setPassword(password);
//        datasource.setDriverClassName(driverClassName);
//
//        // configuration
////        datasource.setInitialSize(initialSize);
////        datasource.setMinIdle(minIdle);
////        datasource.setMaxActive(maxActive);
////        datasource.setMaxWait(maxWait);
////        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
////        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
////        datasource.setValidationQuery(validationQuery);
////        datasource.setTestWhileIdle(testWhileIdle);
////        datasource.setTestOnBorrow(testOnBorrow);
////        datasource.setTestOnReturn(testOnReturn);
////        datasource.setPoolPreparedStatements(poolPreparedStatements);
////        datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
////        try {
////            datasource.setFilters(filters);
////        } catch (SQLException e) {
////            logger.error("druid configuration initialization filter", e);
////        }
////        datasource.setConnectionProperties(connectionProperties);
//        return datasource;
//    }
//}
