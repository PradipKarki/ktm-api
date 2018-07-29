package com.ktm.reference.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.ktm.dictionary.Reference;
import com.ktm.exception.ReferenceValueNotFoundException;
import com.ktm.reference.model.ReferenceValue;
import com.ktm.reference.service.impl.ReferenceServiceImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReferenceCache {

  private static ReferenceServiceImpl referenceServiceImpl;
  private static final LoadingCache<Reference, List<ReferenceValue>> cache =
      Caffeine.newBuilder()
              .maximumSize(1000L)
              .expireAfterAccess(1L, TimeUnit.DAYS)
              .build(key -> referenceServiceImpl.getReferenceValues(key));

  @Autowired
  private ReferenceCache(ReferenceServiceImpl referenceServiceImpl) {
    ReferenceCache.referenceServiceImpl = referenceServiceImpl;
  }

  private ReferenceCache(ReferenceServiceImpl referenceServiceImpl, boolean initialize) {
    this(referenceServiceImpl);
    if (initialize) {
      Arrays.stream(Reference.values()).forEach(cache::get);
    }
  }

  public static List<ReferenceValue> findByReference(Reference reference) {
    try {
      return cache.get(reference);
    } catch (Exception e) {
      throw new ReferenceValueNotFoundException
          (String.format("%s%s", e.getMessage(), reference.getCode()), e);
    }
  }

  public static ReferenceValue findByReferenceAndCode(Reference reference, String
      referenceValueCode) {
    try {
      return Objects.requireNonNull(cache.get(reference))
                    .stream()
                    .filter(r -> Objects.equals(r.getReferenceValueCode(), referenceValueCode))
                    .findFirst()
                    .orElse(null);
    } catch (Exception e) {
      throw new ReferenceValueNotFoundException
          (String.format("%s%s", e.getMessage(), reference.getCode()), e);
    }
  }


}