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
import com.jt.web.entity.Item;
import com.jt.web.service.CartService;
import com.jt.web.service.ItemService;
import com.jt.web.threadLocal.UserThreadLocal;

/*@Controller*/
@RequestMapping("/cart")
public class CartController{

	@Autowired
	private CartService cartService;
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/show")
	public String show(Model model){
		List<Cart> carts = cartService.show(UserThreadLocal.getUserId());
		model.addAttribute("carts",carts);
		return "cart";
	}
	
	@ResponseBody
	@RequestMapping("/add")
	public SysResult addCart(Cart cart){
		try {
			Item baseItem = itemService.getItemById(cart.getItemId());
			cart.setUserId(UserThreadLocal.getUserId());
			cart.setItemPrice(baseItem.getPrice());
			cart.setItemTitle(baseItem.getTitle());
			return cartService.addCart(cart);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "");
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
