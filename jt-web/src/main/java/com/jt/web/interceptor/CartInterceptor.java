package com.jt.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.util.CookieUtils;
import com.jt.web.entity.User;
import com.jt.web.service.HttpClientService;
import com.jt.web.threadLocal.UserThreadLocal;

public class CartInterceptor implements HandlerInterceptor {
	@Autowired
	private HttpClientService httpClientService;

	private static final ObjectMapper MAPPER = new ObjectMapper();

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String ticket = CookieUtils.getCookieValue(request, "JT_TICKET");
		if (StringUtils.isNotEmpty(ticket)) {
			String url = "http://sso.jt.com/user/checkTicket/" + ticket;
			try {
				String jsonData = httpClientService.doGet(url);
				JsonNode jsonNode = MAPPER.readTree(jsonData);
				User curUser = MAPPER.readValue(jsonNode.get("data").asText(), User.class);
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
