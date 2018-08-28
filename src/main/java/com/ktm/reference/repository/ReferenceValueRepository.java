package com.ktm.reference.repository;

import com.ktm.reference.model.ReferenceValue;
import com.ktm.reference.model.ReferenceValueCompositeKey;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReferenceValueRepository
    extends JpaRepository<ReferenceValue, ReferenceValueCompositeKey> {
  List<ReferenceValue> findReferenceValuesByReferenceValueCompositeKeyReferenceTypeCode(
      String code);
}
