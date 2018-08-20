package com.ktm.rest.directory.model;

import com.ktm.rest.BaseEntity;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
public class Directory implements BaseEntity<Long> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "DIRECTORY_ID")
  private Long id;

  private String name;
  private String website;
  private String location;
  private String description;

  private Boolean isVerified;

  @ElementCollection
  @CollectionTable(name = "DIRECTORY_TAG", joinColumns = @JoinColumn(name = "DIRECTORY_ID"))
  @Column(name = "TAG")
  private List<String> tags;

  @OneToOne(cascade = CascadeType.ALL, mappedBy = "directory")
  private DirectoryBusinessHours directoryBusinessHours;

  @OneToOne(cascade = CascadeType.ALL, mappedBy = "directory")
  private DirectoryOverrideHours directoryOverrideHours;
}
