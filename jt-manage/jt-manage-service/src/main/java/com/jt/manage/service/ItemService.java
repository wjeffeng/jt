package com.jt.manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jt.common.service.base.BaseService;
import com.jt.common.vo.EasyUIResult;
import com.jt.manage.dao.ItemDao;
import com.jt.manage.entity.Item;

@Service
public class ItemService extends BaseService<Item> {
	
	@Autowired
	private ItemDao itemDao;
	
	public EasyUIResult queryItemList(Integer pageNum,Integer pageSize){
		PageHelper.startPage(pageNum, pageSize);
		List<Item> itemList = itemDao.queryItemList();
		PageInfo<Item> pageInfo = new PageInfo<>(itemList);
		return new EasyUIResult(pageInfo.getTotal(), pageInfo.getList());
	}
}
