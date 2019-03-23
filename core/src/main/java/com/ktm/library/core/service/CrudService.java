package com.ktm.library.core.service;

import com.ktm.library.core.model.BaseEntity;
import java.io.Serializable;
import java.util.Optional;

public interface CrudService<T extends BaseEntity, ID extends Serializable> {

  T create(T entity);

  Optional<T> find(ID id);

  T update(T entity);

  void delete(T entity);
}
