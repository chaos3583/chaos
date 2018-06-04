package com.chaos.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class Interceptor extends HandlerInterceptorAdapter{

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
}
