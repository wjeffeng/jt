package com.jt.manage.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jt.common.util.JSONUtils;
import com.jt.facade.manage.entity.ItemCat;
import com.jt.facade.manage.service.ItemCatService;

@Controller
@RequestMapping("/item/cat")
public class ItemCatController {

	@Autowired
	private ItemCatService itemCatService;

	@ResponseBody
	@RequestMapping("/findAll")
	public JSONObject findAll() {
		JSONObject result = new JSONObject();
		List<ItemCat> list = this.itemCatService.queryAll();

		result.put("rows", JSONArray.toJSON(list));
		result.put("total",list.size() );
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/findByParam")
	public Object findByParam() {
		ItemCat it = new ItemCat();
		it.setParentId(7L);
		List<ItemCat>  list = this.itemCatService.queryListByWhere(it);
		Object obj = JSONObject.toJSON(list);
		return obj;
	}
	
	@ResponseBody
	@RequestMapping("/list")
	public JSON list(@RequestParam(defaultValue="0") Long id){
		List<ItemCat> list = this.itemCatService.list(id);
		return JSONUtils.dateFormat(list);
	}
}
