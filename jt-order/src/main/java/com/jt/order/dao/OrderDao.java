package com.jt.order.dao;

import com.jt.order.entity.Order;
import com.jt.order.entity.OrderItem;
import com.jt.order.entity.OrderShipping;

public interface OrderDao{
	
	Order getOrder(String orderId);
	
	OrderShipping getOrderShippingByOrderId(String orderId);
	
	OrderItem getOrderItemByOrderId(String orderId);

	void create(Order order);
}