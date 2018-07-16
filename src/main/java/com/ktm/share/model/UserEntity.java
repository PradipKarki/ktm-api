package com.ktm.share.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @NotEmpty
  private String entityType;
  @NotEmpty
  private java.lang.String userId;
  private java.lang.String fullName;
  private java.lang.String emailAddress;
  @NotEmpty
  @JsonIgnore
  @Column(nullable = false, updatable = false)
  @LastModifiedDate
  private LocalDateTime lastModifiedDate;
  @JsonIgnore
  @Column(nullable = false, updatable = false)
  @CreatedDate
  private LocalDateTime createdDate;

  public UserEntity(String entityType, String userId, String fullName, String emailAddress) {
    this.entityType = entityType;
    this.userId = userId;
    this.fullName = fullName;
    this.emailAddress = emailAddress;
  }

}
