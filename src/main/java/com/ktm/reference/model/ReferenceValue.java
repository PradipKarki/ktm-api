package com.ktm.reference.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
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
public class ReferenceValue {

  @EmbeddedId
  private ReferenceValueCompositeKey referenceValueCompositeKey;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "CODE")
  @OnDelete(action = OnDeleteAction.CASCADE)
  @NotNull
  private ReferenceType referenceType;
  private String description;
  @NotNull
  private LocalDateTime effectivePeriodFrom;
  private LocalDateTime effectivePeriodTo;
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

  public String getReferenceValueCode() {
    return this.getReferenceValueCompositeKey().getReferenceValueCode();
  }

  public String getReferenceTypeCode() {
    return this.getReferenceValueCompositeKey().getReferenceTypeCode();
  }

}
