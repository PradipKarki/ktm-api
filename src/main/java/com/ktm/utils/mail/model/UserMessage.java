package com.ktm.utils.mail.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class UserMessage {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "EMAIL_ID")
  private long id;

  @NotEmpty
  @Size(min = 4)
  private String name;
  @NotEmpty
  @Email
  private String email;
  private String phone;
  @NotEmpty
  private String messageCategory;
  @NotEmpty
  private String message;
  @NotEmpty
  private String subject;
  @Column(nullable = false, updatable = false)
  @LastModifiedDate
  private LocalDateTime lastModifiedDate;
  @Column(nullable = false, updatable = false)
  @CreatedDate
  private LocalDateTime createdDate;

  public UserMessage() {
  }

  public UserMessage(String name, String email, String phone, String messageCategory, String message,
                     String subject) {
    super();
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.messageCategory = messageCategory;
    this.message = message;
    this.subject = subject;
  }

  public long getId() {
    return this.id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return this.phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getMessageCategory() {
    return this.messageCategory;
  }

  public void setMessageCategory(String messageCategory) {
    this.messageCategory = messageCategory;
  }

  public String getMessage() {
    return this.message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getSubject() {
    return this.subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

}
