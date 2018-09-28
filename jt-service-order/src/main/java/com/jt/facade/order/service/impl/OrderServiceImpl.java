package com.jt.facade.order.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.common.service.base.BaseServiceImpl;
import com.jt.facade.order.entity.Order;
import com.jt.facade.order.service.OrderService;
import com.jt.service.order.dao.OrderDao;

@Service("orderService")
public class OrderServiceImpl extends BaseServiceImpl<Order> implements OrderService{

	@Autowired
	private OrderDao orderDao;
	
	public Order getOrder(String orderId){
		return orderDao.getOrder(orderId);
	}
	
	@Override
	public String create(Order order){
		String orderId = order.getUserId()+""+System.currentTimeMillis();
		order.setOrderId(orderId);
		order.setCreateTime(new Date());
		order.setUpdateTime(order.getCreateTime());
		orderDao.create(order);
		return orderId;
	}

	@Override
	public void handleUnPaidOrder(Date date) {
		// TODO Auto-generated method stub
		
	}

}
