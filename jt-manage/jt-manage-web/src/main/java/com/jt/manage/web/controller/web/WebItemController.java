package com.jt.manage.web.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.facade.manage.entity.Item;
import com.jt.facade.manage.entity.ItemDesc;
import com.jt.facade.manage.service.ItemService;

@Controller
@RequestMapping("/web/item")
public class WebItemController {

	@Autowired
	private ItemService itemService;
	
	@ResponseBody
	@RequestMapping("/{itemId}")
	public Item getItem(@PathVariable Long itemId){
		return itemService.queryById(itemId);
		
	}
	@ResponseBody
	@RequestMapping("/desc/{itemId}")
	public ItemDesc getItemDesc(@PathVariable Long itemId){
		return itemService.getItemDescById(itemId);
		
	}
}
