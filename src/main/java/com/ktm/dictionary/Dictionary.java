package com.ktm.dictionary;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public interface Dictionary<K extends Serializable, V extends Serializable> {

  K getCode();

  V getDescription();

  V getDescriptionFromCode(K k);

  default String getDictionaryDescription(Integer key, Enum<? extends Dictionary> e) {
    Optional<? extends Dictionary> dictionary =
        Arrays.stream(e.getDeclaringClass().getEnumConstants())
            .filter(b -> Objects.equals(b.getCode(), key))
            .findFirst();
    if (dictionary.isPresent()) {
      return String.valueOf(dictionary.get().getDescription());
    }
    return "";
  }
}
