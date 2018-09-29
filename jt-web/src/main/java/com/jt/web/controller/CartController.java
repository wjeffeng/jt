package com.jt.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.SysResult;
import com.jt.facade.cart.entity.Cart;
import com.jt.facade.cart.service.CartService;
import com.jt.facade.manage.entity.Item;
import com.jt.facade.manage.service.ItemService;
import com.jt.web.annotation.CheckSign;
import com.jt.web.threadLocal.UserThreadLocal;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;
	@Autowired
	private ItemService itemService;

	@RequestMapping("/show")
	public String show(Model model) {
		List<Cart> carts = cartService.myCart(UserThreadLocal.getUserId());
		model.addAttribute("carts", carts);
		return "cart";
	}

	@ResponseBody
	@RequestMapping("/add")
	public SysResult addCart(Cart cart) {
		try {
			Item baseItem = itemService.queryById(cart.getItemId());
			cart.setUserId(UserThreadLocal.getUserId());
			cart.setItemPrice(baseItem.getPrice());
			cart.setItemTitle(baseItem.getTitle());
			cartService.addCart(cart);
			return SysResult.build(200, "保存到购物车成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "保存到购物车失败");
	}

	@ResponseBody
	@RequestMapping("/update/num/{userId}/{itemId}/{num}")
	public SysResult updateNum(Cart cart) {
		try {
			cartService.updateNum(cart);
			return SysResult.build(200, "修改购物车商品成功");
		} catch (Exception e) {
			return SysResult.build(201, "修改购物车商品失败");
		}
	}

	@ResponseBody
	@RequestMapping("/delete/{userId}/{itemId}")
	public SysResult deleteCart(Cart cart) {
		try {
			cartService.deleteByWhere(cart);
			return SysResult.build(200, "删除购物车商品成功");
		} catch (Exception e) {
			return SysResult.build(201, "删除购物车商品失败");
		}
	}

	@CheckSign
	@ResponseBody
	@RequestMapping("/test/{sign}")
	public SysResult test(@PathVariable String sign) {
		return SysResult.ok(sign);
	}
}
