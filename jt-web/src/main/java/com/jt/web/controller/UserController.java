package com.jt.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@RequestMapping("/register")
	public String register(){
		return "register";
	}
	
	@RequestMapping("/login")
	public String login(){
		return "login";
	}
	
}
