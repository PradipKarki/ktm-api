package com.ktm.library.core.reference.model;

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
  private static final long serialVersionUID = 1L;

  private String referenceTypeCode;
  private String referenceValueCode;

}
