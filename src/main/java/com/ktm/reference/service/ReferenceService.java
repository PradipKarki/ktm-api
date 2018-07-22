package com.ktm.reference.service;

import com.ktm.dictionary.Reference;
import com.ktm.reference.model.ReferenceType;
import com.ktm.reference.model.ReferenceValue;
import com.ktm.reference.model.ReferenceValueCompositeKey;
import com.ktm.reference.repository.ReferenceTypeRepository;
import com.ktm.reference.repository.ReferenceValueRepository;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReferenceService {

  @Autowired
  ReferenceTypeRepository referenceTypeRepository;
  @Autowired
  ReferenceValueRepository referenceValueRepository;

  public String getReferenceTypeDescription(Reference reference) {
    Optional<ReferenceType> referenceType = referenceTypeRepository.findById(reference
        .getReferenceTypeCode());
    return referenceType.isPresent() ? referenceType.get().getValue() : "";
  }

  public List<ReferenceValue> getReferenceValues(Reference reference) {
    return referenceValueRepository
        .findReferenceValuesByReferenceValueCompositeKeyReferenceTypeCode(reference
            .getReferenceTypeCode());
  }

  public Map<ReferenceValueCompositeKey, ReferenceValue> getReferenceValuesWithKey(
      Reference reference) {
    return getReferenceValues(reference).stream().collect(Collectors.toMap
        (ReferenceValue::getReferenceValueCompositeKey, Function.identity()));
  }

  public List<ReferenceValue> loadReferenceValues() {
    return referenceValueRepository.findAll();
  }

  public List<ReferenceType> loadReferenceTypes() {
    return referenceTypeRepository.findAll();
  }


}
