package com.ktm.library.core.reference.service;

import com.ktm.library.core.dictionary.Reference;
import com.ktm.library.core.reference.model.ReferenceType;
import com.ktm.library.core.reference.model.ReferenceValue;
import com.ktm.library.core.reference.model.ReferenceValueCompositeKey;
import java.util.List;
import java.util.Map;

public interface ReferenceService {

  String getReferenceTypeDescription(Reference reference);

  List<ReferenceValue> getReferenceValues(Reference reference);

  Map<ReferenceValueCompositeKey, ReferenceValue> getReferenceValuesWithKey(Reference reference);

  List<ReferenceValue> loadReferenceValues();

  List<ReferenceType> loadReferenceTypes();
}
