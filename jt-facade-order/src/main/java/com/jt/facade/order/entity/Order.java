package com.jt.facade.order.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Table;

import com.jt.common.pojo.base.BaseEntity;

@Table(name="tb_order")
public class Order extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 2535811803937543839L;
	
	public static final String FIELD_ORDER_ID = "order_id";
	public static final String FIELD_PAYMENT = "payment";
	public static final String FIELD_PAYMENT_TYPE = "payment_type";
	public static final String FIELD_POST_FEE = "post_fee";
	public static final String FIELD_STATUS = "status";
	public static final String FIELD_CREATE_TIME = "create_time";
	public static final String FIELD_UPDATE_TIME = "update_time";
	public static final String FIELD_PAYMENT_TIME = "payment_time";
	public static final String FIELD_CONSIGN_TIME = "consign_time";
	public static final String FIELD_END_TIME = "end_time";
	public static final String FIELD_CLOSE_TIME = "close_time";
	public static final String FIELD_SHIPPING_NAME = "shipping_name";
	public static final String FIELD_SHIPPING_CODE = "shipping_code";
	public static final String FIELD_USER_ID = "user_id";
	public static final String FIELD_BUYER_MESSAGE = "buyer_message";
	public static final String FIELD_BUYER_NICK = "buyer_nick";
	public static final String FIELD_BUYER_RATE = "buyer_rate";
	
	private OrderShipping orderShipping;
	private List<OrderItem> orderItems;
	
	@Id
	private String orderId; //订单id
	private String payment; //实付金额。精确到2位小数;单位:元。如:200.07，表示:200元7分
	private Integer paymentType; //支付类型，1、在线支付，2、货到付款
	private String postFee; //邮费。精确到2位小数;单位:元。如:200.07，表示:200元7分
	private Integer status; //状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭
	private Date createTime; //订单创建时间
	private Date updateTime; //订单更新时间
	private Date paymentTime; //付款时间
	private Date consignTime; //发货时间
	private Date endTime; //交易完成时间
	private Date closeTime; //交易关闭时间
	private String shippingName; //物流名称
	private String shippingCode; //物流单号
	private Long userId; //用户id
	private String buyerMessage; //买家留言
	private String buyerNick; //买家昵称
	private Integer buyerRate; //买家是否已经评价
	
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderId() {
		return this.orderId;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getPayment() {
		return this.payment;
	}
	public void setPaymentType(Integer paymentType) {
		this.paymentType = paymentType;
	}
	public Integer getPaymentType() {
		return this.paymentType;
	}
	public void setPostFee(String postFee) {
		this.postFee = postFee;
	}
	public String getPostFee() {
		return this.postFee;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getStatus() {
		return this.status;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getCreateTime() {
		return this.createTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Date getUpdateTime() {
		return this.updateTime;
	}
	public void setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
	}
	public Date getPaymentTime() {
		return this.paymentTime;
	}
	public void setConsignTime(Date consignTime) {
		this.consignTime = consignTime;
	}
	public Date getConsignTime() {
		return this.consignTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getEndTime() {
		return this.endTime;
	}
	public void setCloseTime(Date closeTime) {
		this.closeTime = closeTime;
	}
	public Date getCloseTime() {
		return this.closeTime;
	}
	public void setShippingName(String shippingName) {
		this.shippingName = shippingName;
	}
	public String getShippingName() {
		return this.shippingName;
	}
	public void setShippingCode(String shippingCode) {
		this.shippingCode = shippingCode;
	}
	public String getShippingCode() {
		return this.shippingCode;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getUserId() {
		return this.userId;
	}
	public void setBuyerMessage(String buyerMessage) {
		this.buyerMessage = buyerMessage;
	}
	public String getBuyerMessage() {
		return this.buyerMessage;
	}
	public void setBuyerNick(String buyerNick) {
		this.buyerNick = buyerNick;
	}
	public String getBuyerNick() {
		return this.buyerNick;
	}
	public void setBuyerRate(Integer buyerRate) {
		this.buyerRate = buyerRate;
	}
	public Integer getBuyerRate() {
		return this.buyerRate;
	}
	public OrderShipping getOrderShipping() {
		return orderShipping;
	}
	public void setOrderShipping(OrderShipping orderShipping) {
		this.orderShipping = orderShipping;
	}
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	
}