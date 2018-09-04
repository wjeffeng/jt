package com.jt.web.threadLocal;

import com.jt.web.entity.User;

public class UserThreadLocal {
	
	private static ThreadLocal<User> USER = new ThreadLocal<User>();
	
	public static void setUser(User user){
		USER.set(user);
	}
	
	public static User getUser(){
		return USER.get();
	}
	
	public static Long getUserId(){
		if(getUser()!=null&&getUser().getId()!=null){
			return getUser().getId();
		}
		return null;
	}

}
