package com.jt.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jt.common.vo.SysResult;
import com.jt.web.entity.Cart;
import com.jt.web.entity.Order;
import com.jt.web.service.OrderService;
import com.jt.web.threadLocal.UserThreadLocal;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/cart")
	public String orderCart(Model model){
		List<Cart> carts = orderService.getCartList(UserThreadLocal.getUserId());
		model.addAttribute("carts", carts);
		return "order-cart";
	}
	
	@RequestMapping("/submit")
	public SysResult submit(Order order){
		order.setUserId(UserThreadLocal.getUserId());
		return SysResult.ok(orderService.createOrder(order));
	}
}
