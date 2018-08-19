package com.jt.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.HttpClientService;
import com.jt.web.entity.Item;

@Service
public class ItemService {
	
	@Autowired
	private HttpClientService httpClientService;
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	
	public Item getItemById(Long itemId){
		String url = "http://manage.jt.com/web/item/"+itemId;
		Item item = null;
		try {
			String data = httpClientService.doGet(url);
			item = MAPPER.readValue(data, Item.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return item;
	}
}
