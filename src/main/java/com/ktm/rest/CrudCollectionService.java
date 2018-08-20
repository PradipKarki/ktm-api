package com.ktm.rest;

import java.util.List;

public interface CrudCollectionService<T extends BaseEntity> {
  List<T> createAll(List<T> entity);

  List<T> retrieveAll();

  List<T> updateAll(List<T> entity);

  void deleteAll(List<T> entity);
}
