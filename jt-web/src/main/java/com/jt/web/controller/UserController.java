package com.jt.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.util.CookieUtils;
import com.jt.common.vo.SysResult;
import com.jt.web.entity.User;
import com.jt.web.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping("/register")
	public String register() {
		return "register";
	}

	@ResponseBody
	@RequestMapping("/doSignUp")
	public SysResult signUp(User user) {
		try {
			return SysResult.ok(userService.signUp(user));
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.build(201, "参数异常");
		}
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@ResponseBody
	@RequestMapping("/doLogin")
	public SysResult doLogin(HttpServletRequest request,HttpServletResponse response,User user) {
		String ticket = null;
		try {
			ticket = userService.doLogin(user);
			request.getHeader("referer");
			CookieUtils.setCookie(request, response, "JT_TICKET", ticket,60*60*3);
			return SysResult.ok(ticket);
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.build(201, "参数异常");
		}
	}
	
	@RequestMapping("/doLogout")
	public String doLogout(HttpServletRequest request,HttpServletResponse response){
		CookieUtils.deleteCookie(request, response, "JT_TICKET");
		return "index";
	}
}
