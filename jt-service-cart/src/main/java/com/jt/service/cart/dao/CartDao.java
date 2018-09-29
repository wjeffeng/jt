package com.jt.service.cart.dao;

import java.util.Map;

import com.jt.common.dao.base.BaseDao;
import com.jt.facade.cart.entity.Cart;

public interface CartDao extends BaseDao<Cart>{

	Integer updateNum(Map<String, Object> params);

}