package com.ktm.library.core.model.email;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ktm.library.core.model.BaseEntity;
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
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
public class EmailSubscriber implements BaseEntity<Long> {
  private static final long serialVersionUID = 1L;

  @NotEmpty private Boolean isSubscribed;
  @NotEmpty private Boolean isConfirmed;
  @NotEmpty private String verificationToken;
  @NotEmpty private LocalDateTime expirationDate;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "EMAIL_SUBSCRIBER_ID")
  private Long id;

  @NotEmpty(message = "emailAddress address can not be empty")
  @Email
  private String emailAddress;

  @JsonIgnore
  @Column(nullable = false, updatable = false)
  @LastModifiedDate
  private LocalDateTime lastModifiedDate;

  @JsonIgnore
  @Column(nullable = false, updatable = false)
  @CreatedDate
  private LocalDateTime createdDate;

  public EmailSubscriber(String emailAddress, boolean isSubscribed) {
    super();
    this.emailAddress = emailAddress;
    this.isSubscribed = isSubscribed;
    this.isConfirmed = false;
    this.verificationToken = UUID.randomUUID().toString();
    this.expirationDate = LocalDateTime.now().plusWeeks(1L);
  }
}
