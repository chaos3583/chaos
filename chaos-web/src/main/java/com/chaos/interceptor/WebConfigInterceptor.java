//package com.chaos.interceptor;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
//@Configuration
//public class WebConfigInterceptor extends WebMvcConfigurerAdapter{
//
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		// TODO Auto-generated method stub
//		InterceptorRegistration ar = registry.addInterceptor(new Interceptor());
//		// addPathPatterns 用于添加拦截规则
//        // excludePathPatterns 用户排除拦截
////		ar.addPathPatterns("/**");
////		ar.excludePathPatterns("/");
////		ar.excludePathPatterns("/login");
////		ar.excludePathPatterns("www.baidu.com");
//		super.addInterceptors(registry);
//	}
//}
