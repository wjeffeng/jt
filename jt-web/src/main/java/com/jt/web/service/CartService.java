package com.jt.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.vo.SysResult;
import com.jt.web.entity.Cart;
import com.jt.web.entity.Item;

@Service
public class CartService {

	@Autowired
	private HttpClientService httpClientService;
	
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	public static final String domainName = "http://cart.jt.com"; 
	
	public List<Cart> show(Long userId){
		String url = domainName+"/cart/query/"+userId;
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

	public SysResult updateNum(Cart cart) {
		String url = domainName+"/cart/update/num?"
				+"userId="+cart.getUserId()
				+"&itemId="+cart.getItemId()
				+"&num="+cart.getNum();
		try {
			String jsonData = httpClientService.doGet(url);
			JsonNode result = MAPPER.readTree(jsonData);
			return SysResult.build(result.get("status").asInt(), result.get("msg").asText());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "");
	}

	public SysResult deleteCart(Cart cart) {
		String url = domainName+"/cart/deleteCart?"
				+"userId="+cart.getUserId()
				+"&itemId="+cart.getItemId();
		try {
			String jsonData = httpClientService.doGet(url);
			JsonNode result = MAPPER.readTree(jsonData);
			return SysResult.build(result.get("status").asInt(), result.get("msg").asText());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "");
	}

	public SysResult addCart(Cart cart) {
		String url = domainName+"/cart/addCart";
		Map<String,String> params = new HashMap<>();
		params.put("itemId", cart.getItemId().toString());
		//params.put("userId", cart.getUserId().toString());
		params.put("num", cart.getNum().toString());
		try {
			String jsonData = httpClientService.doPost(url, params);
			JsonNode result = MAPPER.readTree(jsonData);
			return SysResult.build(result.get("status").asInt(), result.get("msg").asText());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "");
	}
}
