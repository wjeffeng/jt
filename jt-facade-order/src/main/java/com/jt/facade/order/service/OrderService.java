package com.jt.facade.order.service;

import java.util.Date;

import com.jt.common.service.base.BaseService;
import com.jt.facade.order.entity.Order;

public interface OrderService extends BaseService<Order>{
	
	Order getOrder(String orderId);

	String create(Order order);
	
	void handleUnPaidOrder(Date date);

}
