package com.jt.facade.manage.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemCatResult {
	@JsonProperty("data")
	private List<ItemCatData> itemCatDataList;

	public List<ItemCatData> getItemCatDataList() {
		return itemCatDataList;
	}

	public void setItemCatDataList(List<ItemCatData> itemCatDataList) {
		this.itemCatDataList = itemCatDataList;
	}

}
