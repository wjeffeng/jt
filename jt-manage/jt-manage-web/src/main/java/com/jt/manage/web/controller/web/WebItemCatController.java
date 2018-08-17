package com.jt.manage.web.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.manage.entity.ItemCatResult;
import com.jt.manage.service.ItemCatService;

@Controller
@RequestMapping("/web/itemcat")
public class WebItemCatController {
	@Autowired
	private ItemCatService itemCatService;

	//
	@ResponseBody
	@RequestMapping("/all")
	public ItemCatResult getItemCatList() {
		return itemCatService.getItemCatList();
	}
}
