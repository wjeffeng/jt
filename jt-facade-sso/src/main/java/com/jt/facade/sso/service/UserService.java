package com.jt.facade.sso.service;

import com.jt.common.service.base.BaseService;
import com.jt.common.vo.SysResult;
import com.jt.facade.sso.entity.User;

/**
 * 
 * @author Lion.z
 * @date 2018-08-23 23:06:48
 */
public interface UserService extends BaseService<User>{

	public Boolean check(String param, Integer type) ;

	public String queryLogin(String name,String pwd);

	public SysResult signUp(User user);

	public String doLogin(User user);
	
	public SysResult checkTicket(String ticket);

	void m1(User user, User user2);
}