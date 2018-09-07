package com.jt.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.order.dao.OrderDao;
import com.jt.order.entity.Order;
import com.jt.order.entity.OrderItem;
import com.jt.order.entity.OrderShipping;

@Service
public class OrderService {

	@Autowired
	private OrderDao orderDao;
	
	public Order getOrder(String orderId){
		return orderDao.getOrder(orderId);
	}
	
	public String create(Order order){
		String orderId = order.getUserId()+System.currentTimeMillis()+"";
		order.setOrderId(orderId);
		orderDao.create(order);
		return orderId;
	}

	public OrderShipping getOrderShipping(String orderId) {
		return orderDao.getOrderShippingByOrderId(orderId);
	}

	public OrderItem getOrderItem(String orderId) {
		return orderDao.getOrderItemByOrderId(orderId);
	}
}
