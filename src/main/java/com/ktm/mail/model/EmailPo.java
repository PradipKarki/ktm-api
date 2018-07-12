package com.ktm.mail.model;

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
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "EMAIL")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = "sendAt", allowGetters = true)
@Data
@NoArgsConstructor
public final class EmailPo {

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

  public EmailPo(String toName, String toAddress, String text, String htmlText, String subject) {
    this.toName = toName;
    this.toAddress = toAddress;
    this.text = text;
    this.htmlText = htmlText;
    this.subject = subject;
  }

}
