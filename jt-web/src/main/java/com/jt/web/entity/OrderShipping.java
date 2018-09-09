package com.jt.web.entity;

import java.util.Date;

import com.jt.common.pojo.base.BaseEntity;

public class OrderShipping extends BaseEntity{
	
	public static final String FIELD_ORDER_ID = "order_id";
	public static final String FIELD_RECEIVER_NAME = "receiver_name";
	public static final String FIELD_RECEIVER_PHONE = "receiver_phone";
	public static final String FIELD_RECEIVER_MOBILE = "receiver_mobile";
	public static final String FIELD_RECEIVER_STATE = "receiver_state";
	public static final String FIELD_RECEIVER_CITY = "receiver_city";
	public static final String FIELD_RECEIVER_DISTRICT = "receiver_district";
	public static final String FIELD_RECEIVER_ADDRESS = "receiver_address";
	public static final String FIELD_RECEIVER_ZIP = "receiver_zip";
	public static final String FIELD_CREATED = "created";
	public static final String FIELD_UPDATED = "updated";
	
	private String orderId; //订单ID
	private String receiverName; //收货人全名
	private String receiverPhone; //固定电话
	private String receiverMobile; //移动电话
	private String receiverState; //省份
	private String receiverCity; //城市
	private String receiverDistrict; //区/县
	private String receiverAddress; //收货地址，如：xx路xx号
	private String receiverZip; //邮政编码,如：310001
	private Date created; //
	private Date updated; //
	
	public OrderShipping() {
		super();
	}

	public OrderShipping(String orderId) {
		this.orderId = orderId;
	}
	
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderId() {
		return this.orderId;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getReceiverName() {
		return this.receiverName;
	}
	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}
	public String getReceiverPhone() {
		return this.receiverPhone;
	}
	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}
	public String getReceiverMobile() {
		return this.receiverMobile;
	}
	public void setReceiverState(String receiverState) {
		this.receiverState = receiverState;
	}
	public String getReceiverState() {
		return this.receiverState;
	}
	public void setReceiverCity(String receiverCity) {
		this.receiverCity = receiverCity;
	}
	public String getReceiverCity() {
		return this.receiverCity;
	}
	public void setReceiverDistrict(String receiverDistrict) {
		this.receiverDistrict = receiverDistrict;
	}
	public String getReceiverDistrict() {
		return this.receiverDistrict;
	}
	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}
	public String getReceiverAddress() {
		return this.receiverAddress;
	}
	public void setReceiverZip(String receiverZip) {
		this.receiverZip = receiverZip;
	}
	public String getReceiverZip() {
		return this.receiverZip;
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