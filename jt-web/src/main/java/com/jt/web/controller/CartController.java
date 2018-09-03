package com.jt.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.RedisService;
import com.jt.common.util.CookieUtils;
import com.jt.common.vo.SysResult;
import com.jt.web.annotation.CheckSign;
import com.jt.web.entity.Cart;
import com.jt.web.entity.Item;
import com.jt.web.entity.User;
import com.jt.web.service.CartService;
import com.jt.web.service.ItemService;
import com.jt.web.service.UserService;

@Controller
@RequestMapping("/cart")
public class CartController{

	@Autowired
	private CartService cartService;
	@Autowired
	private ItemService itemService;
	@Autowired
	private RedisService redisService;
	@Autowired
	private UserService userService;
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	@RequestMapping("/show")
	public String show(Model model){
		Long userId = 7l;
		List<Cart> cartList = cartService.show(userId);
		model.addAttribute("cartList",cartList);
		return "cart";
	}
	
	@ResponseBody
	@RequestMapping("/add")
	public SysResult addCart(Cart cart,HttpServletRequest request){
		try {
			User user = userService.getCurrentUser(request);
			Item base = itemService.getItemById(cart.getId());
			cart.setUserId(user.getId());
			return SysResult.ok(cartService.addCart(cart));
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
