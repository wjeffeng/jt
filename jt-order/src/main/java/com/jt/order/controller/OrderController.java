package com.jt.order.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.vo.SysResult;
import com.jt.order.entity.Order;
import com.jt.order.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	public static final ObjectMapper MAPPER = new ObjectMapper(); 
	
	@ResponseBody
	@RequestMapping("/getOrder/{orderId}")
	public SysResult getOrder(@PathVariable String orderId){
		return SysResult.ok(orderService.getOrder(orderId));
	}
	
	//创建订单
	@ResponseBody
	@RequestMapping("/create")
	public String create(@RequestBody String orderData) throws JsonParseException, JsonMappingException, IOException{
		Long userId = 502l;
		Order order = MAPPER.readValue(orderData,Order.class);
		order.setUserId(userId);
		return orderService.create(order);
	}
}
