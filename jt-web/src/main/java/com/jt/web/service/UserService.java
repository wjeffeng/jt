package com.jt.web.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.vo.SysResult;
import com.jt.web.entity.User;

@Service
public class UserService {
	
	@Autowired
	private HttpClientService httpClientService;

	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	public SysResult signUp(User user) throws Exception{
		Map<String,String> params = new HashMap<>();
		params.put("username", user.getUsername());
		params.put("password", user.getPassword());
		params.put("phone", user.getPhone());
		params.put("email", user.getEmail());
		String url = "http://sso.jt.com/user/doSignUp";
		try {
			String jsonData = httpClientService.doPost(url, params);
			JsonNode jsonNode = MAPPER.readTree(jsonData);
			return SysResult.build(jsonNode.get("status").asInt(), jsonNode.get("msg").asText());
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.build(201,"");
		}
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
	
	public User getCurrentUser(String ticket){
		String url = "http://sso.jt.com/user/query/checkTicket/"+ticket;
		String jsonData;
		JsonNode userData;
		try {
			jsonData = httpClientService.doGet(url);
			userData = MAPPER.readTree(jsonData).get("data");
			User user = MAPPER.readValue(userData.asText(), User.class);
			if(user!=null){
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
