package com.ktm.library.core.model.email;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ktm.library.core.model.BaseEntity;
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
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "EMAIL")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = "sendAt", allowGetters = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public final class EmailPo implements BaseEntity<Long> {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "EMAIL_ID")
  private Long id;

  @Transient private String fromName;
  @Email private String fromAddress;
  @Transient private String toName;
  @NotEmpty @Email private String toAddress;
  @Transient private String bounceToAddress;
  @Transient private transient List<Recipient> recipients;

  private String text;
  private String htmlText;

  private String subject;

  @Column(nullable = false, updatable = false)
  @CreatedDate
  private LocalDateTime sendAt;

  @Transient private String base64String;
  @Transient private Map<String, String> headers;

  @JsonIgnore
  @Column(nullable = false, updatable = false)
  @LastModifiedDate
  private LocalDateTime lastModifiedDate;

  @JsonIgnore
  @Column(nullable = false, updatable = false)
  @CreatedDate
  private LocalDateTime createdDate;
}
