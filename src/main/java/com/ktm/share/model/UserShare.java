package com.ktm.share.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import com.ktm.utils.MediaType;

@Entity
public class UserShare extends UserEntityInfo {
	@Id
	@Column(name="USER_SHARE_ID", nullable=false)
	private long id;
	@NotEmpty
	private String socialMediaName;
	
	public UserShare(String entityId, MediaType entityType, String userId, String shareLocation) {
		super(entityId, entityType, userId);
		this.socialMediaName = shareLocation;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getShareLocation() {
		return this.socialMediaName;
	}

	public void setShareLocation(String shareLocation) {
		this.socialMediaName = shareLocation;
	}
	
}
