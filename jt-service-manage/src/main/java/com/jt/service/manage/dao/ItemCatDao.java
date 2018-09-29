package com.jt.service.manage.dao;

import java.util.List;

import com.jt.common.dao.base.BaseDao;
import com.jt.facade.manage.entity.ItemCat;

public interface ItemCatDao extends BaseDao<ItemCat>{

	List<ItemCat> queryItemList();
	
}