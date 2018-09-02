package com.jt.cart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.cart.entity.Cart;
import com.jt.cart.service.CartService;
import com.jt.common.vo.SysResult;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;
	
	@ResponseBody
	@RequestMapping("/query/{userId}")
	public SysResult myCart(@PathVariable Long userId){
		List<Cart> list = cartService.myCart(userId);
		return SysResult.ok(list);
	}
	
	@ResponseBody
	@RequestMapping("/save")
	public SysResult saveCart(Cart cart){
		try {
			cartService.saveCart(cart);
			return SysResult.build(200,"保存到购物车成功");
		} catch (Exception e) {
			return SysResult.build(201,"保存到购物车失败");
		}
	}
	
	@ResponseBody
	@RequestMapping("/update/num")
	public SysResult updateNum(Cart cart){
		try {
			cartService.updateNum(cart);
			return SysResult.build(200,"修改购物车商品成功");
		} catch (Exception e) {
			return SysResult.build(201,"修改购物车商品失败");
		}
	}
	
	@ResponseBody
	@RequestMapping("/deleteCart")
	public SysResult deleteCart(Cart cart){
		try {
			cartService.deleteByWhere(cart);
			return SysResult.build(200,"删除购物车商品成功");
		} catch (Exception e) {
			return SysResult.build(201,"删除购物车商品失败");
		}
	}
}
