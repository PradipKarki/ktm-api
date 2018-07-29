package com.ktm.rest;

import java.io.Serializable;

public interface BaseEntity<E extends Serializable> extends Serializable {

  E getId();
}
