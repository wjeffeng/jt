package com.jt.web.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.RedisService;
import com.jt.common.util.CookieUtils;
import com.jt.web.entity.User;

@Service
public class UserService {
	
	@Autowired
	private HttpClientService httpClientService;
	@Autowired
	private RedisService redisService;

	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	public String signUp(User user) throws Exception{
		Map<String,String> params = new HashMap<>();
		params.put("username", user.getUsername());
		params.put("password", user.getPassword());
		params.put("phone", user.getPhone());
		params.put("email", user.getEmail());
		String url = "http://sso.jt.com/user/doSignUp";
		String username = null;
		try {
			String jsonData = httpClientService.doPost(url, params);
			JsonNode jsonNode = MAPPER.readTree(jsonData);
			username = jsonNode.get("data").asText();
		} catch (Exception e) {
			throw e;
		}
		return username;
	}

	public String doLogin(User user) throws Exception {
		Map<String,String> params = new HashMap<>();
		params.put("username", user.getUsername());
		params.put("password", user.getPassword());
		
		String url = "http://sso.jt.com/user/doLogin";
		String ticket = null;
		try {
			String jsonData = httpClientService.doPost(url, params);
			ticket = MAPPER.readTree(jsonData).get("data").asText();
		} catch (Exception e) {
			throw e;
		}
		return ticket;
	}
	
	public User getCurrentUser(HttpServletRequest request){
		String ticket = CookieUtils.getCookieValue(request, "JT_TICKET");
		String userData = redisService.get(ticket);
		try {
			User user = MAPPER.readValue(userData, User.class);
			if(user!=null){
				return user;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
