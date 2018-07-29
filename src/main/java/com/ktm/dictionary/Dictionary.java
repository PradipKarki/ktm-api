package com.ktm.dictionary;

import java.io.Serializable;

public interface Dictionary<K extends Serializable, V extends Serializable> {

  K getCode();

  V getDescription();

  V getDescriptionFromCode(K k);
}
