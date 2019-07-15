package com.chaos.app;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;


@Configuration
@EnableAutoConfiguration
@EnableAsync
@MapperScan(basePackages = {"com.shawearn.demo.persistence"})
@ComponentScan(basePackages = {"com.chaos"})
@PropertySource({"classpath:application.properties"})
//@EnableTSharding(mapperPackage={"com.chaos.dao"})
//@EnableMethodCache(basePackages = "com.chaos")
//@EnableCreateCacheAnnotation
public class Application extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

//    // 用于处理编码问题
//    @Bean
//    public Filter characterEncodingFilter() {
//        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
//        characterEncodingFilter.setEncoding("UTF-8");
//        characterEncodingFilter.setForceEncoding(true);
//        return characterEncodingFilter;
//    }
}

