package com.ktm.share.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.ktm.utils.MediaType;

@Entity
public class UserLike extends UserEntityInfo {
	@Id
	@Column(name="USER_LIKE_ID", nullable=false)
	private long id;

	public UserLike(String entityId, MediaType entityType, String userId) {
		super(entityId, entityType, userId);
	}
	
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
