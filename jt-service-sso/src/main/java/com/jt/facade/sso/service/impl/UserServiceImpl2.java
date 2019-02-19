package com.jt.facade.sso.service.impl;

import org.springframework.stereotype.Service;

import com.jt.common.service.base.BaseServiceImpl;
import com.jt.facade.sso.entity.User;
import com.jt.facade.sso.service.UserService2;

@Service("userService2")
public class UserServiceImpl2 extends BaseServiceImpl<User> implements UserService2{
	
	@Override
	public void m2(User user2){
		user2.setPhone("BBB");
		update(user2);
		if(user2.getUsername().contains("b")){
			return;	
		}
		throw new RuntimeException();
	}
}