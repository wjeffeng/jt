package com.jt.common.pojo.base;

import java.time.LocalDateTime;

public class BaseEntity {

	private LocalDateTime updated;
	private LocalDateTime created;

	public LocalDateTime getUpdated() {
		return updated;
	}

	public void setUpdated(LocalDateTime updated) {
		this.updated = updated;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

}
