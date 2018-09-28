package com.jt.facade.order.entity;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Lion.z
 * @date 2018-09-05 05:30:01
 */
@Table(name="tb_order_item")
public class OrderItem implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public static final String FIELD_ITEM_ID = "item_id";
	public static final String FIELD_ORDER_ID = "order_id";
	public static final String FIELD_NUM = "num";
	public static final String FIELD_TITLE = "title";
	public static final String FIELD_PRICE = "price";
	public static final String FIELD_TOTAL_FEE = "total_fee";
	public static final String FIELD_PIC_PATH = "pic_path";
	
	@Id
	private String itemId; //商品id
	@Id
	private String orderId; //订单id
	private Integer num; //商品购买数量
	private String title; //商品标题
	private Long price; //商品单价
	private Long totalFee; //商品总金额
	private String picPath; //商品图片地址
	
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getItemId() {
		return this.itemId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderId() {
		return this.orderId;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Integer getNum() {
		return this.num;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle() {
		return this.title;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public Long getPrice() {
		return this.price;
	}
	public void setTotalFee(Long totalFee) {
		this.totalFee = totalFee;
	}
	public Long getTotalFee() {
		return this.totalFee;
	}
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	public String getPicPath() {
		return this.picPath;
	}
	
}