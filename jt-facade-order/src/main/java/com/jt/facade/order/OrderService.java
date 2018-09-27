package com.jt.facade.order;

import java.util.Date;

import com.jt.order.entity.Order;

public interface OrderService {
	
	Order getOrder(String orderId);

	String create(Order order);
	
	void handleUnPaidOrder(Date date);

}
