package com.jt.facade.manage.service;

import com.jt.common.service.base.BaseService;
import com.jt.common.vo.EasyUIResult;
import com.jt.facade.manage.entity.Item;
import com.jt.facade.manage.entity.ItemDesc;

public interface ItemService extends BaseService<Item>{
	
	public EasyUIResult queryItemList(Integer pageNum,Integer pageSize);
	
	public void saveItem(Item item,String desc);
	
	public void updateItem(Item item,String desc);
	
	public void deleteItem(Long[] ids);

	public void updateStatus(int i, Long[] ids) ;

	public ItemDesc getItemDescById(Long id) ;
}
