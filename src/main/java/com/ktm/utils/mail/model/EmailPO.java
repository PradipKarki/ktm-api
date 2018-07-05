package com.ktm.utils.mail.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import org.simplejavamail.email.Recipient;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "EMAIL")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = "sendAt", allowGetters = true)
public final class EmailPO {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "EMAIL_ID")
  private long id;

  @Transient
  private String fromName;
  @NotEmpty
  @Email
  private String fromAddress;

  @Transient
  private String toName;
  @Transient
  @NotEmpty
  @Email
  private String toAddress;
  @Transient
  private String bounceToAddress;
  @Transient
  private List<Recipient> recipients;

  private String text;
  // sample htmlText -> "<img src='cid:wink1'><b>We should meet up!</b><img
  // src='cid:wink2'>"
  private String htmlText;
  @NotEmpty
  private String subject;
  @Column(nullable = false, updatable = false)
  @CreatedDate
  private LocalDateTime sendAt;

  @Transient
  private String base64String;
  @Transient
  private Map<String, String> headers;

  public EmailPO() {
  }

  public EmailPO(String toName, String toAddress, String text, String htmlText, String subject) {
    this.toName = toName;
    this.toAddress = toAddress;
    this.text = text;
    this.htmlText = htmlText;
    this.subject = subject;
  }

  public long getId() {
    return this.id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getFromName() {
    return this.fromName;
  }

  public void setFromName(String fromName) {
    this.fromName = fromName;
  }

  public String getFromAddress() {
    return this.fromAddress;
  }

  public void setFromAddress(String fromAddress) {
    this.fromAddress = fromAddress;
  }

  public String getToName() {
    return this.toName;
  }

  public void setToName(String toName) {
    this.toName = toName;
  }

  public String getToAddress() {
    return this.toAddress;
  }

  public void setToAddress(String toAddress) {
    this.toAddress = toAddress;
  }

  public String getBounceToAddress() {
    return this.bounceToAddress;
  }

  public void setBounceToAddress(String bounceToAddress) {
    this.bounceToAddress = bounceToAddress;
  }

  public List<Recipient> getRecipients() {
    return this.recipients;
  }

  public void setRecipients(List<Recipient> recipients) {
    this.recipients = recipients;
  }

  public String getText() {
    return this.text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getSubject() {
    return this.subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getBase64String() {
    return this.base64String;
  }

  public void setBase64String(String base64String) {
    this.base64String = base64String;
  }

  public Map<String, String> getHeaders() {
    return this.headers;
  }

  public void setHeaders(Map<String, String> headers) {
    this.headers = headers;
  }

  public String getHtmlText() {
    return this.htmlText;
  }

  public void setHtmlText(String htmlText) {
    this.htmlText = htmlText;
  }

  public LocalDateTime getSendAt() {
    return this.sendAt;
  }

  public void setSendAt(LocalDateTime sendAt) {
    this.sendAt = sendAt;
  }

}
