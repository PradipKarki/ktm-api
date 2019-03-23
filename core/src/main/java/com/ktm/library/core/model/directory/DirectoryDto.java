package com.ktm.library.core.model.directory;

import com.ktm.library.core.dictionary.BusinessCategory;
import com.ktm.library.core.model.person.Address;
import com.ktm.library.core.model.person.EmailAddress;
import com.ktm.library.core.model.person.Person;
import com.ktm.library.core.model.person.PhoneNumber;
import java.util.List;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Class to expose endpoints in place of Directory for security purpose */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DirectoryDto {

  @Enumerated(EnumType.STRING)
  private BusinessCategory businessCategory;

  private String description;
  private String fax;
  private Boolean isVerified;
  private String name;
  private String website;

  private List<PhoneNumber> phoneNumbers;
  private List<EmailAddress> emailAddresses;
  private List<Address> addresses;
  private List<Person> contactPersons;
  private List<SocialMediaUrl> socialMediaUrl;
  private List<String> tags;

  private DirectoryBusinessHours directoryBusinessHours;
  private DirectoryOverrideHours directoryOverrideHours;
}
