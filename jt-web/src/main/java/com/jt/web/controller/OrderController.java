package com.jt.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.SysResult;
import com.jt.facade.cart.entity.Cart;
import com.jt.facade.cart.service.CartService;
import com.jt.facade.order.entity.Order;
import com.jt.facade.order.service.OrderService;
import com.jt.web.threadLocal.UserThreadLocal;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private CartService cartService;
	
	@RequestMapping("/cart")
	public String orderCart(Model model){
		List<Cart> carts = cartService.myCart(UserThreadLocal.getUserId());
		model.addAttribute("carts", carts);
		return "order-cart";
	}
	
	@ResponseBody
	@RequestMapping("/submit")
	public SysResult submit(Order order){ 
		order.setUserId(UserThreadLocal.getUserId());
		return SysResult.ok(orderService.create(order));
	}
	
	@RequestMapping("/getOrderById")
	public SysResult getOrderById(Long orderId){
		try {
			return SysResult.ok(orderService.getOrder(orderId.toString()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("/success")
	public String success(Model model,Long id){
		Order order=null;
		try {
			order = orderService.getOrder(id.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("order", order);
		return "success";
	}
}
