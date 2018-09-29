package com.jt.facade.cart.service;

import java.util.List;

import com.jt.common.service.base.BaseService;
import com.jt.facade.cart.entity.Cart;

public interface CartService extends BaseService<Cart>{

	//
	public List<Cart> myCart(Long userId) ;

	// 保存商品到购物车
	public Integer addCart(Cart cart);
	
	//修改商品数量
	public Integer updateNum(Cart cart) ;
}
