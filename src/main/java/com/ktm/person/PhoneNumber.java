package com.ktm.person;

import com.ktm.rest.BaseEntity;
import com.ktm.rest.directory.model.Directory;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneNumber implements BaseEntity<Long> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "PHONE_NUMBER_ID")
  private Long id;

  @Column(name = "PHONE_NUMBER")
  private String phoneNum;

  @ManyToOne
  @JoinColumn(name = "FK_ID", insertable = false, updatable = false)
  private Directory directory;

  @ManyToOne
  @JoinColumn(name = "FK_ID", insertable = false, updatable = false)
  private Person person;
}
