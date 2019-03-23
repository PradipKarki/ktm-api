package com.ktm.library.core.model.person;

import com.ktm.library.core.model.BaseEntity;
import com.ktm.library.core.model.directory.Directory;
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
public class EmailAddress implements BaseEntity<Long> {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "EMAIL_ADDRESS_ID")
  private Long id;

  @Column(name = "EMAIL_ADDRESS")
  private String email;

  @ManyToOne
  @JoinColumn(name = "FK_ID", insertable = false, updatable = false)
  private Directory directory;

  @ManyToOne
  @JoinColumn(name = "FK_ID", insertable = false, updatable = false)
  private Person person;
}
