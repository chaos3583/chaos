package com.chaos.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

/**
 * 设置连接信息；
 *
 * Created by liaopeng
 */
@Configuration
public class DruidDBConfig {
    private final static Logger logger = LoggerFactory.getLogger(DruidDBConfig.class);
    @Value("${spring.datasource.url}")
    private String dbUrl;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.driverClassName}")
    private String driverClassName;

    @Bean     //声明其为Bean实例
//    @Primary  //在同样的DataSource中，首先使用被标注的DataSource
    @ConfigurationProperties(prefix ="jdbc")
    public DataSource dataSource() {
        DruidDataSource datasource = new DruidDataSource();

        datasource.setUrl(this.dbUrl);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);
        return datasource;
    }
}
