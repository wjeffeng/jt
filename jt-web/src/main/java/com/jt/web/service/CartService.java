package com.jt.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.web.entity.Cart;

@Service
public class CartService {

	@Autowired
	private HttpClientService httpClientService;
	
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	public List<Cart> show(Long userId){
		String url = "http://cart.jt.com/cart/query/"+userId;
		try {
			String jsonData = httpClientService.doGet(url);
			JsonNode cartListJson = MAPPER.readTree(jsonData).get("data");
			Object obj = null;
			if(cartListJson.isArray()&&cartListJson.size()>0){
				obj = MAPPER.readValue(cartListJson.traverse(),
						MAPPER.getTypeFactory().constructCollectionType(List.class, Cart.class));
			}
			return (List<Cart>) obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
