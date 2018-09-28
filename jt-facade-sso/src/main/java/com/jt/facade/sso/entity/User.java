package com.jt.facade.sso.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jt.common.pojo.base.BaseEntity;

@Table(name="tb_user")
public class User extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = -9121463519515287526L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public static final String FIELD_ID = "id";
	public static final String FIELD_USERNAME = "username";
	
	@JsonIgnore
	public static final String FIELD_PASSWORD = "password";
	public static final String FIELD_PHONE = "phone";
	public static final String FIELD_EMAIL = "email";
	public static final String FIELD_CREATED = "created";
	public static final String FIELD_UPDATED = "updated";

	private Long id; //
	private String username; //
	private String password; // MD5加密
	private String phone; //
	private String email; //
	private Date created; //
	private Date updated; //

	public User() {
		super();
	}

	public User(Long id) {
		this.id = id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return this.username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
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
