package com.jt.manage.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.EasyUIResult;
import com.jt.common.vo.SysResult;
import com.jt.manage.entity.Item;
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
	
	@ResponseBody
	@RequestMapping("/save")
	public SysResult saveItem(Item item,String desc){
		try {
			itemService.saveItem(item,desc);
			return SysResult.ok();
		} catch (Exception e) {
			return SysResult.build(201, "新增商品出错");
		}
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public SysResult updateItem(Item item){
		try {
			itemService.updateItem(item);
			return SysResult.ok();
		} catch (Exception e) {
			return SysResult.build(201, "");
		}
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public SysResult deleteItem(Long[] ids){
		try {
			itemService.deleteById(ids);
			return SysResult.ok();
		} catch (Exception e) {
			return SysResult.build(201, "");
		}
	}
	
	@ResponseBody
	@RequestMapping("/reshelf")
	public SysResult reshelf(Long[] ids){
		try {
			itemService.updateStatus(1,ids);
			return SysResult.ok();
		} catch (Exception e) {
			return SysResult.build(201, "");
		}
	}
}
