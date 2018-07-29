package com.ktm.rest;

import java.io.Serializable;

public interface CrudService<E extends Serializable, T extends BaseEntity> {
  T create(T entity);

  T read(E primaryKey);

  void update(T entity);

  void delete(T entity);
}
