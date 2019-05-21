package com.jt.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.jt.facade.sso.entity.User;
import com.jt.facade.sso.service.UserService;

@Controller
@RequestMapping("/test")
public class TestController {
	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping("/update")
	public JSONObject m1() {
		JSONObject result = new JSONObject();
		User user = userService.queryById(502l);
		User user2 = userService.queryById(503l);
		userService.m1(user,user2);
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/doTest")
	public JSONObject doTest(@Valid User user){
		JSONObject result = new JSONObject();
		result.put("user", user);
		return result;
	}
	
	
}
