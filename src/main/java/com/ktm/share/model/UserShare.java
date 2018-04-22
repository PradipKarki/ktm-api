package com.ktm.share.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.ktm.utils.KtmSharableEntity;

@Entity
public class UserShare extends UserEntityInfo {
	@Id
	@Column(name="USER_SHARE_ID", nullable=false)
	private long id;
	private String shareLocation;
	
	public UserShare(String entityId, KtmSharableEntity entityType, String userId, String shareLocation) {
		super(entityId, entityType, userId);
		this.shareLocation = shareLocation;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getShareLocation() {
		return this.shareLocation;
	}

	public void setShareLocation(String shareLocation) {
		this.shareLocation = shareLocation;
	}
	
}
