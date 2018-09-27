package com.jt.order.dao;

import java.util.Date;

import com.jt.common.dao.base.BaseDao;
import com.jt.order.entity.Order;

public interface OrderDao extends BaseDao<Order>{
	
	Order getOrder(String orderId);

	void create(Order order);
	
	void handleUnPaidOrder(Date date);
}