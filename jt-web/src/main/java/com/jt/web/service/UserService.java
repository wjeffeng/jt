package com.jt.web.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.web.entity.User;

@Service
public class UserService {
	
	@Autowired
	private HttpClientService httpClientService;
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
}
