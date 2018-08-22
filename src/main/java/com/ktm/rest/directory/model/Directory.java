package com.ktm.rest.directory.model;

import com.ktm.dictionary.BusinessCategory;
import com.ktm.person.Address;
import com.ktm.person.EmailAddress;
import com.ktm.person.Person;
import com.ktm.person.PhoneNumber;
import com.ktm.rest.BaseEntity;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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

  @Enumerated(EnumType.STRING)
  private BusinessCategory businessCategory;

  private String description;
  private String fax;
  private Boolean isVerified;
  private String name;
  private String website;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "directory", cascade = CascadeType.ALL)
  private List<PhoneNumber> phoneNumbers;
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "directory", cascade = CascadeType.ALL)
  private List<EmailAddress> emailAddresses;
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "directory", cascade = CascadeType.ALL)
  private List<Address> addresses;
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "directory", cascade = CascadeType.ALL)
  private List<Person> contactPersons;
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "directory", cascade = CascadeType.ALL)
  private List<SocialMediaUrl> socialMediaUrl;

  @ElementCollection
  @CollectionTable(name = "DIRECTORY_TAG", joinColumns = @JoinColumn(name = "DIRECTORY_ID"))
  @Column(name = "TAG")
  private List<String> tags;

  @OneToOne(cascade = CascadeType.ALL, mappedBy = "directory")
  private DirectoryBusinessHours directoryBusinessHours;
  @OneToOne(cascade = CascadeType.ALL, mappedBy = "directory")
  private DirectoryOverrideHours directoryOverrideHours;
}
