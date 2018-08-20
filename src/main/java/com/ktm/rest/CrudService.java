package com.ktm.rest;

import java.io.Serializable;
import java.util.Optional;

public interface CrudService<E extends Serializable, T extends BaseEntity> {
  T create(T entity);

  Optional<T> read(E primaryKey);

  T update(T entity);

  void delete(T entity);
}
