package com.jt.manage.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jt.common.service.base.BaseServiceImpl;
import com.jt.common.vo.EasyUIResult;
import com.jt.manage.dao.ItemDao;
import com.jt.manage.dao.ItemDescDao;
import com.jt.manage.entity.Item;
import com.jt.manage.entity.ItemDesc;

@Service
public class ItemService extends BaseServiceImpl<Item> {
	
	@Autowired
	private ItemDao itemDao;
	@Autowired
	private ItemDescDao itemDescDao;
	
	public EasyUIResult queryItemList(Integer pageNum,Integer pageSize){
		PageHelper.startPage(pageNum, pageSize);
		List<Item> itemList = itemDao.queryItemList();
		PageInfo<Item> pageInfo = new PageInfo<>(itemList);
		return new EasyUIResult(pageInfo.getTotal(), pageInfo.getList());
	}
	
	public void saveItem(Item item,String desc) {
		item.setStatus(1);
		item.setUpdated(new Date());
		item.setCreated(item.getUpdated());
		
		itemDao.insertSelective(item);
		//新增商品详情
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemId(item.getId());
		itemDesc.setItemDesc(desc);
		itemDesc.setUpdated(item.getUpdated());
		itemDesc.setCreated(item.getCreated());
		
		itemDescDao.insertSelective(itemDesc);
		
	}
	
	public void updateItem(Item item,String desc){
		item.setUpdated(new Date());
		
		itemDao.updateByPrimaryKeySelective(item);
		
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemId(item.getId());
		itemDesc.setItemDesc(desc);
		itemDesc.setUpdated(item.getUpdated());
		
		itemDescDao.updateByPrimaryKey(itemDesc);
	}
	
	public void deleteItem(Long[] ids){
		itemDescDao.deleteByIDS(ids);
		itemDao.deleteByIDS(ids);
	}

	public void updateStatus(int i, Long[] ids) {
		Item item = new Item();
		item.setStatus(1);
		for (Long id : ids) {
			item.setId(id);
			itemDao.updateByPrimaryKeySelective(item);
		}
	}

	public ItemDesc getItemDescById(Long id) {
		return itemDescDao.selectByPrimaryKey(id);
	}
}
