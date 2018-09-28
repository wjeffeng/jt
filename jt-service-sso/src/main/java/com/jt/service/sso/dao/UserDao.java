package com.jt.service.sso.dao;

import java.util.Map;

import com.jt.common.dao.base.BaseDao;
import com.jt.facade.sso.entity.User;

public interface UserDao extends BaseDao<User>{

	Integer check(Map<String, Object> params);

}