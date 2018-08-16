package com.jt.manage.web.controller.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.manage.entity.ItemCat;
import com.jt.manage.entity.ItemCatData;
import com.jt.manage.entity.ItemCatResult;
import com.jt.manage.service.ItemCatService;

@Controller
@RequestMapping("/web/item/cat")
public class WebItemCatController {
	@Autowired
	private ItemCatService itemCatService;

	//
	@ResponseBody
	@RequestMapping("/list")
	public ItemCatResult getItemCatList() {
		ItemCat params = new ItemCat();
		params.setStatus(1);
		List<ItemCat> itemCatList = itemCatService.queryListByWhere(params);

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
					items3.add(getUrl(itemCat3) + itemCat3.getName());
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
		result.setItems(list);
		return result;
	}

	private String getUrl(ItemCat itemCat) {
		return "/products/" + itemCat.getId() + ".html";
	}

	private String getNameTag(ItemCat itemCat, String url) {
		return "<a href=\"" + url + "\">" + itemCat.getName() + "</a>";
	}

}
