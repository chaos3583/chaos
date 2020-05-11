package com.chaos.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Aspect//使之成为切面类
@Component//将切面类加入到IOC容器中
public class Interceptor extends HandlerInterceptorAdapter{

	/**
	 * 切点，
	 */
	public static final String  POINT = "execution (* com.chaos.controller..*.*(..))";


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("aaaaaaaaa");
		Object attribute = request.getSession().getAttribute("user_token");
//		User loginUser = (User) request.getSession().getAttribute("user_token");
		if(attribute!=null) {
			return true;
		}
		response.sendRedirect("/");
		return false;
	}

	/**
	 * 前置通知
	 */
	@Before(POINT)
	public void before(){
		System.out.println("前置通知");
	}

	/**
	 * 后置通知
	 */
	@After(POINT)
	public void after(){
		System.out.println("后置通知");
	}

	/**
	 * 环绕通知
	 * @param point
	 */
	@Around(POINT)
	public void doAround(ProceedingJoinPoint point){
		try {
			System.out.println("环绕通知前");
			point.proceed();
			MethodSignature signature = (MethodSignature) point.getSignature();
			String methodName = signature.getDeclaringTypeName() + "." + signature.getName();
			System.out.println("环绕通知后,方法："+methodName);
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}

	}

}
