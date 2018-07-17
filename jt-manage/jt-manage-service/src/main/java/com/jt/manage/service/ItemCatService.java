package com.jt.manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.common.service.base.BaseService;
import com.jt.manage.dao.ItemCatDao;
import com.jt.manage.entity.ItemCat;

@Service
public class ItemCatService extends BaseService<ItemCat>{
	
	@Autowired
	private ItemCatDao itemCatDao;
	
	public List<ItemCat> list(ItemCat itemCat){
		return itemCatDao.select(itemCat);
	}
	
}
