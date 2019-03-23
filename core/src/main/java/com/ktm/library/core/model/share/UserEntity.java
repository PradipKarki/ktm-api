package com.ktm.library.core.model.share;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ktm.library.core.model.BaseEntity;
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
public class UserEntity implements BaseEntity<Long> {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotEmpty private String entityType;
  @NotEmpty private String userId;
  private String fullName;
  private String emailAddress;

  @NotEmpty
  @JsonIgnore
  @Column(nullable = false, updatable = false)
  @LastModifiedDate
  private LocalDateTime lastModifiedDate;

  @JsonIgnore
  @Column(nullable = false, updatable = false)
  @CreatedDate
  private LocalDateTime createdDate;

  protected UserEntity(String entityType, String userId, String fullName, String emailAddress) {
    this.entityType = entityType;
    this.userId = userId;
    this.fullName = fullName;
    this.emailAddress = emailAddress;
  }
}
