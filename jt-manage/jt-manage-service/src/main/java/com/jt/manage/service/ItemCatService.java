package com.jt.manage.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.RedisService;
import com.jt.common.service.base.BaseServiceImpl;
import com.jt.manage.dao.ItemCatDao;
import com.jt.manage.entity.ItemCat;
import com.jt.manage.entity.ItemCatData;
import com.jt.manage.entity.ItemCatResult;

@Service
public class ItemCatService extends BaseServiceImpl<ItemCat> {

	@Autowired
	private ItemCatDao itemCatDao;
	@Autowired
	private RedisService redisService;

	private static final ObjectMapper MAPPER = new ObjectMapper();
	private static final Logger logger = Logger.getLogger(ItemCatService.class);

	@SuppressWarnings("unchecked")
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
	
	//
	public ItemCatResult getItemCatList() {
		ItemCat params = new ItemCat();
		params.setStatus(1);
		List<ItemCat> itemCatList = queryListByWhere(params);

		Map<Long, List<ItemCat>> map = new HashMap<Long, List<ItemCat>>();
		Long parentId = null;
		for (ItemCat itemCat : itemCatList) {
			parentId = itemCat.getParentId();
			if (!map.containsKey(parentId)) {
				map.put(parentId, new ArrayList<ItemCat>());
			}
			map.get(parentId).add(itemCat);

		}
		
		ItemCatResult result = new ItemCatResult();
		List<ItemCatData> list = new ArrayList<>();
		for (ItemCat itemCat1 : map.get(0L)) {
			ItemCatData itemCatData1 = new ItemCatData();
			itemCatData1.setUrl(getUrl(itemCat1));
			itemCatData1.setName(getNameTag(itemCat1, itemCatData1.getUrl()));
			List<ItemCatData> items2 = new ArrayList<>();
			
			for (ItemCat itemCat2 : map.get(itemCat1.getId())) {
				ItemCatData itemCatData2 = new ItemCatData();
				itemCatData2.setUrl(getUrl(itemCat2));
				itemCatData2.setName(getNameTag(itemCat2, itemCatData2.getUrl()));
				List<String> items3 = new ArrayList<>();
				
				for (ItemCat itemCat3 : map.get(itemCat2.getId())) {
					items3.add(getUrl(itemCat3) +"|"+ itemCat3.getName());
				}
				
				itemCatData2.setItems(items3);
				items2.add(itemCatData2);
			}
			
			itemCatData1.setItems(items2);
			list.add(itemCatData1);
			
			if (list.size() == 14) {
				break;
			}
		}
		result.setItemCatDataList(list);
		return result;
	}

	private String getUrl(ItemCat itemCat) {
		return "/products/" + itemCat.getId() + ".html";
	}

	private String getNameTag(ItemCat itemCat, String url) {
		return "<a href=\"" + url + "\">" + itemCat.getName() + "</a>";
	}


}
