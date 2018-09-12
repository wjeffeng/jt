package com.jt.order.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.order.dao.OrderDao;
import com.jt.order.entity.Order;

@Service
public class OrderService {

	@Autowired
	private OrderDao orderDao;
	
	public Order getOrder(String orderId){
		return orderDao.getOrder(orderId);
	}
	
	public String create(Order order){
		String orderId = order.getUserId()+""+System.currentTimeMillis();
		order.setOrderId(orderId);
		order.setCreateTime(new Date());
		order.setUpdateTime(order.getCreateTime());
		orderDao.create(order);
		return orderId;
	}

}
