package com.jt.web.entity;

import javax.persistence.Id;
import javax.persistence.Table;

import com.jt.common.pojo.base.BaseEntity;

@Table(name="tb_item_desc")
public class ItemDesc extends BaseEntity{
	@Id
	private long itemId;
	private String itemDesc;
	
	public long getItemId() {
		return itemId;
	}
	public void setItemId(long itemId) {
		this.itemId = itemId;
	}
	public String getItemDesc() {
		return itemDesc;
	}
	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}
	
}
