package com.jt.sso.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jt.common.dao.base.BaseDao;
import com.jt.sso.entity.User;

/**
 * 
 * @author Lion.z
 * @date 2018-08-23 23:06:48
 */
@Repository("userDao")
public interface UserDao extends BaseDao<User>{

	Integer check(Map<String, Object> params);

}