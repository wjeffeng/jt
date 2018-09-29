package com.jt.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.util.CookieUtils;
import com.jt.common.vo.SysResult;
import com.jt.facade.sso.entity.User;
import com.jt.facade.sso.service.UserService;
import com.jt.web.threadLocal.UserThreadLocal;

public class UserInterceptor implements HandlerInterceptor {
	@Autowired
	private UserService userService;

	private static final ObjectMapper MAPPER = new ObjectMapper();

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String ticket = CookieUtils.getCookieValue(request, "JT_TICKET");
		if (StringUtils.isNotEmpty(ticket)) {
			try {
				SysResult result =userService.checkTicket(ticket);
				User curUser = MAPPER.readValue(result.getData().toString(), User.class);
				UserThreadLocal.setUser(curUser);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		response.sendRedirect("/user/login.html");
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
