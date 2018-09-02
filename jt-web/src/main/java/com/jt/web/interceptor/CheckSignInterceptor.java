package com.jt.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jt.web.annotation.CheckSign;

public class CheckSignInterceptor extends HandlerInterceptorAdapter{
	public  void doPreHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception{};
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if(handler instanceof HandlerMethod){
			CheckSign cs = ((HandlerMethod) handler).getMethodAnnotation(CheckSign.class);
			if(cs==null){
				return true;
			}else{
				System.out.println("----执行校验----");
				return true;
			}
		}
		System.out.println("----校验失败----");
		return false;
	}
}
