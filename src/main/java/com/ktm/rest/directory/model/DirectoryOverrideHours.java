package com.ktm.rest.directory.model;

import com.ktm.rest.BaseEntity;
import java.time.LocalTime;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DirectoryOverrideHours implements BaseEntity<Long> {
  private static final long serialVersionUID = 1L;

  @Id
  private Long id;

  @JoinColumn(name = "DIRECTORY_ID")
  @OneToOne(fetch = FetchType.LAZY)
  @MapsId
  private Directory directory;

  // uses DayOfWeek ENUM, Monday being 1 and Sunday being 7
  private Integer dayOfWeek;
  private Integer overrideStartDate;
  private Integer overrideEndDate;
  private LocalTime alternateOpenTime;
  private LocalTime alternateCloseTime;
  private Boolean isClosed;
}
