package com.jt.manage.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.manage.entity.EasyUIResult;
import com.jt.manage.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@ResponseBody
	@RequestMapping("/query")
	public EasyUIResult queryItemList(Integer page, Integer rows) {
		EasyUIResult result = itemService.queryItemList(page, rows);
		return result;
	}
}
