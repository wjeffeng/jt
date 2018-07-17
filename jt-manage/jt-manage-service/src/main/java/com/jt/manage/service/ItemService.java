package com.jt.manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.common.service.base.BaseService;
import com.jt.manage.dao.ItemDao;
import com.jt.manage.entity.Item;

@Service
public class ItemService extends BaseService<Item> {
	
	@Autowired
	private ItemDao itemDao;
	
	public List<Item> queryItemList(Integer pageNum,Integer pageSize){
		List<Item> itemList = itemDao.queryItemList();
		return itemList;
	}
}
