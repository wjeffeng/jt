package com.jt.web.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 购物车模块
 * @author Lion.z
 * @date 2018-08-28 28:26:18
 */
public class Cart implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String FIELD_ID = "id";
	public static final String FIELD_USER_ID = "user_id";
	public static final String FIELD_ITEM_ID = "item_id";
	public static final String FIELD_ITEM_TITLE = "item_title";
	public static final String FIELD_ITEM_IMAGE = "item_image";
	public static final String FIELD_ITEM_PRICE = "item_price";
	public static final String FIELD_NUM = "num";
	public static final String FIELD_CREATED = "created";
	public static final String FIELD_UPDATED = "updated";
	
	private Long id; //自增ID
	private Long userId; //用户ID
	private Long itemId; //商品ID
	private String itemTitle; //商品标题
	private String itemImage; //商品主图
	private Long itemPrice; //商品价格，单位为：分
	private Integer num; //购买数量
	private Date created; //
	private Date updated; //
	
	public Cart() {
		super();
	}

	public Cart(Long id) {
		this.id = id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return this.id;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getUserId() {
		return this.userId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public Long getItemId() {
		return this.itemId;
	}
	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}
	public String getItemTitle() {
		return this.itemTitle;
	}
	public void setItemImage(String itemImage) {
		this.itemImage = itemImage;
	}
	public String getItemImage() {
		return this.itemImage;
	}
	public void setItemPrice(Long itemPrice) {
		this.itemPrice = itemPrice;
	}
	public Long getItemPrice() {
		return this.itemPrice;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Integer getNum() {
		return this.num;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getCreated() {
		return this.created;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public Date getUpdated() {
		return this.updated;
	}
	
}