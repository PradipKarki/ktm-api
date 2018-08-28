package com.ktm.person;

import com.ktm.dictionary.AddressType;
import com.ktm.rest.BaseEntity;
import com.ktm.rest.directory.model.Directory;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
public class Address implements BaseEntity<Long> {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ADDRESS_ID")
  private Long id;

  @Enumerated(EnumType.STRING)
  private AddressType addressType;

  private String buildingName;
  private String houseNum;
  private String subBuildingNum;
  private String streetName;
  private String city;
  private String otherDetails;
  private String postalCode;
  private String state;
  private String country;

  @ManyToOne
  @JoinColumn(name = "FK_ID", insertable = false, updatable = false)
  private Person person;

  @ManyToOne
  @JoinColumn(name = "FK_ID", insertable = false, updatable = false)
  private Directory directory;
}
