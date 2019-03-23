package com.ktm.library.core.service;

import com.ktm.library.core.model.BaseEntity;
import com.ktm.library.core.model.twitter.TwitterPo;
import java.io.Serializable;

public interface CrudCollectionService<T extends BaseEntity, ID extends Serializable> {

  Iterable<T> saveAll(Iterable<T> entities);

  Iterable<T> findAll(Iterable<ID> ids);

  Iterable<T> findAll();

  Iterable<T> updateAll(Iterable<T> entities);

  void deleteAll(Iterable<T> entities);
}
