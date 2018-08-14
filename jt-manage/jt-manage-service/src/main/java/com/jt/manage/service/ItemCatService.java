package com.jt.manage.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.RedisService;
import com.jt.common.service.base.BaseService;
import com.jt.manage.dao.ItemCatDao;
import com.jt.manage.entity.ItemCat;

@Service
public class ItemCatService extends BaseService<ItemCat> {

	@Autowired
	private ItemCatDao itemCatDao;
	@Autowired
	private RedisService redisService;

	private static final ObjectMapper MAPPER = new ObjectMapper();
	private static final Logger logger = Logger.getLogger(ItemCatService.class);

	public List<ItemCat> list(Long id) {
		ItemCat itemCat = new ItemCat();
		itemCat.setStatus(1);
		itemCat.setParentId(id);

		List<ItemCat> itemCatList = null;
		String itemCatId = "ITEMCAT_" + id;
		String jsonData = redisService.get(itemCatId);            
		try {
			if (StringUtils.isNotBlank(jsonData)) {
				JsonNode jsonNode = MAPPER.readTree(jsonData);
				Object obj = MAPPER.readValue(jsonNode.traverse(),
						MAPPER.getTypeFactory().constructCollectionType(List.class, ItemCat.class));
				itemCatList = (List<ItemCat>)obj;
			} else {
				itemCatList = itemCatDao.select(itemCat);

				String data = MAPPER.writeValueAsString(itemCatList);
				redisService.set(itemCatId, data);
			}
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}

		return itemCatList;
	}

}
