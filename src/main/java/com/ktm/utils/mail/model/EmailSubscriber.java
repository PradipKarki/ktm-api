package com.ktm.utils.mail.model;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class EmailSubscriber {

    @NotEmpty
    private boolean isSubscribed;
    @NotEmpty
    private boolean isConfirmed;
    @NotEmpty
    private String verifyToken;
    @NotEmpty
    private LocalDateTime expirationDate;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMAIL_SUBSCRIBER_ID")
    private long id;
    @NotEmpty(message = "email address can not be empty")
    @Email
    private String emailAddress;
    @Column(nullable = false, updatable = false)
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
    @Column(nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdDate;

    public EmailSubscriber() {
    }

    public EmailSubscriber(String emailAddress, boolean isSubscribed) {
        super();
        this.emailAddress = emailAddress;
        this.isSubscribed = isSubscribed;
        this.isConfirmed = false;
        this.verifyToken = UUID.randomUUID().toString();
        this.expirationDate = LocalDateTime.now().plusWeeks(1L);
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

    public LocalDateTime getExpirationDate() {
        return this.expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public LocalDateTime getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

}
