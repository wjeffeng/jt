package com.jt.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.SysResult;
import com.jt.web.annotation.CheckSign;
import com.jt.web.entity.Cart;
import com.jt.web.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;
	
	@RequestMapping("/show")
	public String show(Model model){
		Long userId = 7l;
		List<Cart> cartList = cartService.show(userId);
		model.addAttribute("cartList",cartList);
		return "cart";
	}
	
	@ResponseBody
	@RequestMapping("/update/num/{userId}/{itemId}/{num}")
	public SysResult updateNum(Cart cart){
		return cartService.updateNum(cart);
	}
	
	@ResponseBody
	@RequestMapping("/delete/{userId}/{itemId}")
	public SysResult deleteCart(Cart cart){
		return cartService.deleteCart(cart);
	}
	
	@CheckSign
	@ResponseBody
	@RequestMapping("/test/{sign}")
	public SysResult test(@PathVariable String sign){
		return SysResult.ok(sign);
	}
}
