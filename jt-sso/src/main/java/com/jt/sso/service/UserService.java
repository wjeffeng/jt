package com.jt.sso.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.RedisService;
import com.jt.common.service.base.BaseServiceImpl;
import com.jt.sso.dao.UserDao;
import com.jt.sso.entity.User;


/**
 * 
 * @author Lion.z
 * @date 2018-08-23 23:06:48
 */
@Service("userService")
public class UserService extends BaseServiceImpl<User>{

	@Autowired
	private UserDao userDao;
	@Autowired
	private RedisService redisService;
	
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
		
		return 0==i;
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
}