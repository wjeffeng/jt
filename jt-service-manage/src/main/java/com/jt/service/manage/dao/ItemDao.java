package com.jt.service.manage.dao;

import java.util.List;
import java.util.Map;

import com.jt.common.dao.base.BaseDao;
import com.jt.facade.manage.entity.Item;

public interface ItemDao extends BaseDao<Item>{

	List<Item> findItemByPageInfo(Map<String, Integer> pageInfo);

	int selectCountItem();

	List<Item> findAll();

	List<Item> queryItemList();


}
