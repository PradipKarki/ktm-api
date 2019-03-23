package com.ktm.library.core.model;

import java.io.Serializable;

public interface BaseEntity<ID extends Serializable> extends Serializable {

  ID getId();
}
