package com.ktm.reference.service.impl;

import com.ktm.dictionary.Reference;
import com.ktm.reference.model.ReferenceType;
import com.ktm.reference.model.ReferenceValue;
import com.ktm.reference.model.ReferenceValueCompositeKey;
import com.ktm.reference.repository.ReferenceTypeRepository;
import com.ktm.reference.repository.ReferenceValueRepository;
import com.ktm.reference.service.ReferenceService;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReferenceServiceImpl implements ReferenceService {

  @Autowired ReferenceTypeRepository referenceTypeRepository;
  @Autowired ReferenceValueRepository referenceValueRepository;

  @Override
  public String getReferenceTypeDescription(Reference reference) {
    return referenceTypeRepository
        .findById(reference.getCode())
        .map(ReferenceType::getValue)
        .orElse("");
  }

  @Override
  public List<ReferenceValue> getReferenceValues(Reference reference) {
    return referenceValueRepository
        .findReferenceValuesByReferenceValueCompositeKeyReferenceTypeCode(reference.getCode());
  }

  @Override
  public Map<ReferenceValueCompositeKey, ReferenceValue> getReferenceValuesWithKey(
      Reference reference) {
    return getReferenceValues(reference)
        .stream()
        .collect(
            Collectors.toMap(ReferenceValue::getReferenceValueCompositeKey, Function.identity()));
  }

  @Override
  public List<ReferenceValue> loadReferenceValues() {
    return referenceValueRepository.findAll();
  }

  @Override
  public List<ReferenceType> loadReferenceTypes() {
    return referenceTypeRepository.findAll();
  }
}
