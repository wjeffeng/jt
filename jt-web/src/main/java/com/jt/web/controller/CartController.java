package com.jt.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.SysResult;
import com.jt.web.entity.Cart;
import com.jt.web.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;
	
	@ResponseBody
	@RequestMapping("/show/{userId}")
	public SysResult show(@PathVariable Long userId){
		List<Cart> cartList = cartService.show(userId);
		return SysResult.ok(cartList);
	}
}
