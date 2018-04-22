package com.ktm.share.model;

import javax.persistence.Column;
import javax.persistence.Id;

import com.ktm.utils.KtmSharableEntity;

public class UserRating extends UserEntityInfo {
	@Id
	@Column(name="USER_RATING_ID", nullable=false)
	private long id;
	private long userRatingScore;
	
	public UserRating(String entityId, KtmSharableEntity entityType, String userId, long userRatingScore) {
		super(entityId, entityType, userId);
		this.setUserRatingScore(userRatingScore);
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserRatingScore() {
		return this.userRatingScore;
	}

	public void setUserRatingScore(long userRatingScore) {
		this.userRatingScore = userRatingScore;
	}

}
