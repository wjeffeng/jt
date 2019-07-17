package com.jt.sso.controller;

import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.service.RedisService;
import com.jt.common.vo.SysResult;
import com.jt.sso.entity.User;
import com.jt.sso.service.UserService;

/**
 * 
 * @author Lion.z
 * @date 2018-08-23 23:06:49
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private RedisService redisService;
	
	@ResponseBody
	@RequestMapping(value="/check/{param}/{type}",method=RequestMethod.GET)
	public SysResult check(@PathVariable("param") String param,@PathVariable("type") Integer type) {
		try {
			boolean result = this.userService.check(param,type);
			return SysResult.ok(result);
		} catch (Exception e) {
			return SysResult.build(201, "监测失败");
		}
	}
	
	@ResponseBody
	@RequestMapping("/doSignUp")
	public SysResult signUp(User user) {
		//TODO 校验
		if(!userService.check(user.getUsername(),1)){
			return SysResult.build(201, "用户名已注册");
		}
		if(!userService.check(user.getPhone(), 2)){
			return SysResult.build(201, "手机号码已注册");
		}
		if(!userService.check(user.getEmail(), 3)){
			return SysResult.build(201, "邮箱已注册");
		}
		user.setCreated(new Date());
		user.setUpdated(user.getCreated());
		user.setEmail("temp_"+user.getPhone()+"@qq.com");
		user.setPassword(DigestUtils.md5Hex(user.getEmail()));
		try {
			this.userService.saveSelective(user);
			return SysResult.build(200, "注册成功",user.getUsername());
		} catch (Exception e) {
			return SysResult.build(201,"参数异常");
		}
	}
	
	@ResponseBody
	@RequestMapping("/doLogin")
	public SysResult login(User user) {
		try {
			String ticket = this.userService.queryLogin(user.getUsername(),user.getPassword());
			return SysResult.ok(ticket);
		} catch (Exception e) {
			return SysResult.build(201,"参数异常");
		}
	}
	
	@ResponseBody
	@RequestMapping("/checkTicket/{ticket}")
	public SysResult checkTicket(@PathVariable String ticket) {
		try {
			String jsonData = this.redisService.get(ticket);
			return SysResult.ok(jsonData);
		} catch (Exception e) {
			return SysResult.build(201, "参数异常");
		}
	}
}