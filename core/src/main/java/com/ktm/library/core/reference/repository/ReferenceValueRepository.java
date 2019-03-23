package com.ktm.library.core.reference.repository;

import com.ktm.library.core.reference.model.ReferenceValue;
import com.ktm.library.core.reference.model.ReferenceValueCompositeKey;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReferenceValueRepository
    extends JpaRepository<ReferenceValue, ReferenceValueCompositeKey> {
  List<ReferenceValue> findReferenceValuesByReferenceValueCompositeKeyReferenceTypeCode(
      String code);
}
