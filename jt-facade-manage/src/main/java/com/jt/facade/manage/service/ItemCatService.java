package com.jt.facade.manage.service;

import java.util.List;

import com.jt.common.service.base.BaseService;
import com.jt.facade.manage.entity.ItemCat;
import com.jt.facade.manage.entity.ItemCatResult;

public interface ItemCatService extends BaseService<ItemCat>{

	public List<ItemCat> list(Long id) ;
	
	public ItemCatResult getItemCatList() ;

}
