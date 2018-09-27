package com.jt.order.service.Impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.facade.order.OrderService;
import com.jt.order.dao.OrderDao;
import com.jt.order.entity.Order;

@Service("orderService")
public class OrderServiceImpl implements OrderService{

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
