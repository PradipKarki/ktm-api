package com.ktm.library.core.service;

import com.ktm.library.core.model.BaseEntity;
import java.util.List;

public interface Sanitizer<T extends BaseEntity> {

  List<T> processData(List<T> twitterPos);
}
