package com.jt.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jt.web.entity.Item;
import com.jt.web.entity.ItemDesc;
import com.jt.web.service.ItemService;

/*@Controller*/
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService itemService;

	@RequestMapping("/{itemId}")
	public String getItem(@PathVariable Long itemId, Model model) {
		Item item = this.itemService.getItemById(itemId);
		model.addAttribute("item", item);
		return "item";
	}
	
	@RequestMapping("/desc/{itemId}")
	public String getItemDesc(@PathVariable Long itemId, Model model) {
		ItemDesc itemDesc = this.itemService.getItemDescById(itemId);
		model.addAttribute("itemDesc", itemDesc);
		return "item";
	}
}
