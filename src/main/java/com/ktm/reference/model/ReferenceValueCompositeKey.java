package com.ktm.reference.model;

import java.io.Serializable;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReferenceValueCompositeKey implements Serializable {

  private String referenceTypeCode;
  private String referenceValueCode;

}
