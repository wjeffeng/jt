package com.jt.sso.dao;

import java.util.Map;

import com.jt.common.dao.base.BaseDao;
import com.jt.sso.entity.User;

public interface UserDao extends BaseDao<User>{

	Integer check(Map<String, Object> params);

}