package com.ktm.library.core.reference.service.impl;

import static java.util.stream.Collectors.toMap;

import com.ktm.library.core.dictionary.Reference;
import com.ktm.library.core.reference.model.ReferenceType;
import com.ktm.library.core.reference.model.ReferenceValue;
import com.ktm.library.core.reference.model.ReferenceValueCompositeKey;
import com.ktm.library.core.reference.repository.ReferenceTypeRepository;
import com.ktm.library.core.reference.repository.ReferenceValueRepository;
import com.ktm.library.core.reference.service.ReferenceService;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReferenceServiceImpl implements ReferenceService {

  @Autowired private ReferenceTypeRepository referenceTypeRepository;
  @Autowired private ReferenceValueRepository referenceValueRepository;

  @Override
  public String getReferenceTypeDescription(Reference reference) {
    return referenceTypeRepository
        .findById(reference.getCode())
        .map(ReferenceType::getValue)
        .orElse(StringUtils.EMPTY);
  }

  @Override
  public List<ReferenceValue> getReferenceValues(Reference reference) {
    return referenceValueRepository
        .findReferenceValuesByReferenceValueCompositeKeyReferenceTypeCode(reference.getCode());
  }

  @Override
  public Map<ReferenceValueCompositeKey, ReferenceValue> getReferenceValuesWithKey(
      Reference reference) {
    return getReferenceValues(reference).stream()
        .collect(toMap(ReferenceValue::getReferenceValueCompositeKey, Function.identity()));
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
