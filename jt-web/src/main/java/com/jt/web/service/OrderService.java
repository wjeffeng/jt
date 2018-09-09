package com.jt.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.util.ObjToMapUtil;
import com.jt.common.vo.SysResult;
import com.jt.web.entity.Cart;
import com.jt.web.entity.Order;

@Service
public class OrderService {
	
	@Autowired
	private HttpClientService httpClientService;
	public static final ObjectMapper MAPPER = new ObjectMapper();
	
	@SuppressWarnings("unchecked")
	public List<Cart> getCartList(Long userId){
		String url = "http://cart.jt.com/cart/query/"+userId;
		try {
			String jsonData = httpClientService.doGet(url);
			JsonNode dataNode = MAPPER.readTree(jsonData).get("data");
			List<Cart> cartList = null;
			if(dataNode.isArray()&&dataNode.size()>0){
				cartList = (List<Cart>)MAPPER.readValue(dataNode.traverse(),
						MAPPER.getTypeFactory().constructCollectionType(List.class, Cart.class));
			}
			return cartList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String createOrder(Order order) {
		String url = "http://order.jt.com/order/create";
		try {
			String orderId = httpClientService.doPostJson(url, MAPPER.writeValueAsString(order));
			return orderId;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
