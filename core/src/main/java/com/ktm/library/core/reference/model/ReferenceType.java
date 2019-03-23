package com.ktm.library.core.reference.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReferenceType {

  @Id private String code;
  @NotBlank private String value;
  private String description;

  @JsonIgnore
  @Column(nullable = false, updatable = false)
  @LastModifiedDate
  private LocalDateTime lastModifiedDate;

  @JsonIgnore
  @Column(nullable = false, updatable = false)
  @CreatedDate
  private LocalDateTime createdDate;

  private String lastModifiedBy;
  private String createdBy;
}
