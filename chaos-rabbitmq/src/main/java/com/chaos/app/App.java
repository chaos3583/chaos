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
public class App extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
//    @Bean
//	@LoadBalanced
//	RestTemplate restTemplate(){
//		return new RestTemplate();
//	}
//	@Bean
//	public ApplicationContextUtil applicationContextUtilTemp(){
//		return new ApplicationContextUtil();
//	}
}

