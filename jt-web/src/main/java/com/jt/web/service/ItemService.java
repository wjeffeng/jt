package com.jt.web.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.RedisService;
import com.jt.web.entity.Item;
import com.jt.web.entity.ItemDesc;

@Service
public class ItemService {

	@Autowired
	private HttpClientService httpClientService;
	@Autowired
	private RedisService redisService;
	private static final ObjectMapper MAPPER = new ObjectMapper();

	public Item getItemById(Long itemId) {
		Item item = null;
		String data = null;
		try {
			// 增加商品缓存
			String ITEM_KEY = "ITEM_" + itemId;
			data = redisService.get(ITEM_KEY);
			if (StringUtils.isBlank(data)) {
				String url = "http://manage.jt.com/web/item/" + itemId;
				data = httpClientService.doGet(url);
				
				if(StringUtils.isBlank(data)){
					return null;
				}
				
				//写缓存
				redisService.set(ITEM_KEY, data);
			}
			item = MAPPER.readValue(data, Item.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return item;
	}

	public ItemDesc getItemDescById(Long itemId) {
		String url = "http://manage.jt.com/web/item/desc/" + itemId;
		ItemDesc itemDesc = null;
		try {
			String data = httpClientService.doGet(url);
			itemDesc = MAPPER.readValue(data, ItemDesc.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return itemDesc;
	}
}
