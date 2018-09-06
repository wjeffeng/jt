package com.jt.cart.dao;

import java.util.Map;

import com.jt.cart.entity.Cart;
import com.jt.common.dao.base.BaseDao;

public interface CartDao extends BaseDao<Cart>{

	Integer updateNum(Map<String, Object> params);

}