package com.ktm.library.core.model;

import java.io.Serializable;

/** Class to expose endpoints in place of entity objects for security purpose */
public interface BaseDto<ID extends Serializable> {

  ID getId();
}
