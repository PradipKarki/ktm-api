package com.ktm.reference.service;

import com.ktm.dictionary.Reference;
import com.ktm.reference.model.ReferenceType;
import com.ktm.reference.model.ReferenceValue;
import com.ktm.reference.model.ReferenceValueCompositeKey;
import java.util.List;
import java.util.Map;

public interface ReferenceService {

  String getReferenceTypeDescription(Reference reference);

  List<ReferenceValue> getReferenceValues(Reference reference);

  Map<ReferenceValueCompositeKey, ReferenceValue> getReferenceValuesWithKey(Reference reference);

  List<ReferenceValue> loadReferenceValues();

  List<ReferenceType> loadReferenceTypes();
}
