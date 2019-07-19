package com.jt.facade.sso.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.RedisService;
import com.jt.common.service.base.BaseServiceImpl;
import com.jt.common.vo.SysResult;
import com.jt.facade.sso.entity.User;
import com.jt.facade.sso.service.UserService;
import com.jt.service.sso.dao.UserDao;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService{

	@Autowired
	private UserDao userDao;
	@Autowired
	private RedisService redisService;
	@Autowired
	private UserService userService;
	
	public static final ObjectMapper MAPPER = new ObjectMapper();

	public Boolean check(String param, Integer type) {
		Map<String,Object> params = new HashMap<>();
		switch(type){
		case 1:params.put("type", "username");break;
		case 2:params.put("type", "phone");break;
		case 3:params.put("type", "email");break;
		}
		params.put("param",param);
		Integer i = userDao.check(params);
		if(0==i){
			return false;
		}
		return true;
	}

	public String queryLogin(String name,String pwd) {
		User temp = new User();
		temp.setUsername(name);
		User curUser = queryByWhere(temp);
		User cacheUser = new User();
		if(null!=curUser){
			String encryptedPwd = DigestUtils.md5Hex(pwd);
			if(encryptedPwd.equals(curUser.getPassword())){
				String ticket = DigestUtils.md5Hex(System.currentTimeMillis()+curUser.getUsername()+curUser.getId());
				try {
					cacheUser.setId(curUser.getId());
					cacheUser.setUsername(curUser.getUsername());
					cacheUser.setPhone(curUser.getPhone());
					redisService.set(ticket, MAPPER.writeValueAsString(cacheUser),60*60*3);
					return ticket;
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	@Override
	public SysResult signUp(User user) {
		if(this.check(user.getUsername(),1)){
			return SysResult.build(201, "用户名已注册");
		}
		if(this.check(user.getPhone(), 2)){
			return SysResult.build(201, "手机号码已注册");
		}
		if(this.check(user.getEmail(), 3)){
			return SysResult.build(201, "邮箱已注册");
		}
		user.setCreated(new Date());
		user.setUpdated(user.getCreated());
		user.setEmail("temp_"+user.getPhone()+"@qq.com");
		user.setPassword(DigestUtils.md5Hex(user.getEmail()));
		try {
			this.saveSelective(user);
			return SysResult.build(200, "注册成功",user.getUsername());
		} catch (Exception e) {
			return SysResult.build(201,"参数异常");
		}
	}

	@Override
	public String doLogin(User user) {
		try {
			String ticket = this.queryLogin(user.getUsername(),user.getPassword());
			return ticket;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public SysResult checkTicket(String ticket) {
		try {
			String jsonData = this.redisService.get(ticket);
			return SysResult.ok(jsonData);
		} catch (Exception e) {
			return SysResult.build(201, "参数异常");
		}
	}
	
	@Override
	public void m1(User user,User user2){
		user.setPhone("AAA");
		userDao.updateByPrimaryKey(user);
		/*try {
			this.userService2.m2(user2);
		} catch (Exception e) {
			System.out.println("---m2 roll back---");
		}*/
		method2();
		if(user.getUsername().contains("a")){
			return;	
		}
		throw new RuntimeException();
		
	}
	
	private void method2(){
		User u3 = userDao.selectByPrimaryKey(504l);
		u3.setPhone("CCC");
		userDao.updateByPrimaryKey(u3);
		if(u3.getUsername().contains("c")){
			return;	
		}
		throw new RuntimeException();
	}
}