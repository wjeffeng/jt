package com.jt.cart.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jt.cart.entity.Cart;
import com.jt.common.dao.base.BaseDao;

/**
 * 
 * @author Lion.z
 * @date 2018-08-23 23:06:48
 */
@Repository("cartDao")
public interface CartDao extends BaseDao<Cart>{

	Integer updateNum(Map<String, Object> params);

}