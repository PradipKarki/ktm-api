package com.ktm.share.model;

import com.ktm.utils.MediaType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;

@Entity
public class UserRating extends UserEntityInfo {
    @Id
    @Column(name = "USER_RATING_ID", nullable = false)
    private long id;
    @NotEmpty
    @Max(10)
    private int userRatingScore;

    public UserRating(String entityId, MediaType entityType, String userId, int userRatingScore) {
        super(entityId, entityType, userId);
        this.setUserRatingScore(userRatingScore);
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getUserRatingScore() {
        return this.userRatingScore;
    }

    public void setUserRatingScore(int userRatingScore) {
        this.userRatingScore = userRatingScore;
    }

}
