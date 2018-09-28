package com.jt.cart.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.cart.dao.CartDao;
import com.jt.cart.entity.Cart;
import com.jt.common.service.base.BaseServiceImpl;

@Service
public class CartService extends BaseServiceImpl<Cart>{

	@Autowired
	private CartDao cartDao;

	//
	public List<Cart> myCart(Long userId) {
		Cart cart = new Cart();
		cart.setUserId(userId);
		return cartDao.select(cart);
	}

	// 保存商品到购物车
	public Integer addCart(Cart cart){
		//判断此用户的此商品是否存在
		Cart param = new Cart();
		param.setUserId(cart.getUserId());
		param.setItemId(cart.getItemId());
		Cart baseCart = super.queryByWhere(param);
		
		cart.setUpdated(new Date());
		if(baseCart!=null){
			cart.setId(baseCart.getId());
			cart.setNum(baseCart.getNum()+cart.getNum());
			return cartDao.updateByPrimaryKeySelective(cart);
		}
		cart.setCreated(cart.getUpdated());
		return cartDao.insert(cart);
	}
	
	//修改商品数量
	public Integer updateNum(Cart cart) {
		Map<String,Object> params = new HashMap<>();
		params.put(Cart.FIELD_NUM, cart.getNum());
		params.put(Cart.FIELD_USER_ID, cart.getUserId());
		params.put(Cart.FIELD_ITEM_ID, cart.getItemId());
		return cartDao.updateNum(params);
	}
}
