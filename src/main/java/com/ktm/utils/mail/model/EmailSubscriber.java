package com.ktm.utils.mail.model;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class EmailSubscriber {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="EMAIL_SUBSCRIBER_ID")
	private long id;
	@NotEmpty(message = "email address can not be empty")
	@Email
	private String emailAddress;
	@NotEmpty
	boolean isSubscribed;
	@NotEmpty
	boolean isConfirmed;
	@NotEmpty
	String verifyToken;
	@NotEmpty
	Date expirationDate;
    @Column(nullable = false, updatable = false)
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;
    @Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
    private Date createdDate;
    
	public EmailSubscriber() {
		super();
	}

	public EmailSubscriber(String emailAddress, boolean isSubscribed) {
		super();
		this.emailAddress = emailAddress;
		this.isSubscribed = isSubscribed;
		this.isConfirmed = false;
		this.verifyToken = UUID.randomUUID().toString();
		this.expirationDate = Date.from(LocalDateTime.now().plusWeeks(1).toInstant(ZoneOffset.UTC));
	}

	public long getId() {
		return this.id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public boolean isSubscribed() {
		return this.isSubscribed;
	}
	public void setSubscribed(boolean isSubscribed) {
		this.isSubscribed = isSubscribed;
	}

	public boolean isConfirmed() {
		return this.isConfirmed;
	}

	public void setConfirmed(boolean isConfirmed) {
		this.isConfirmed = isConfirmed;
	}

	public String getVerifyToken() {
		return this.verifyToken;
	}

	public void setVerifyToken(String verifyToken) {
		this.verifyToken = verifyToken;
	}

	public Date getExpirationDate() {
		return this.expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Date getLastModifiedDate() {
		return this.lastModifiedDate;
	}
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
}
