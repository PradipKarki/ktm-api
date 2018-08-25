package com.ktm.rest;

import java.util.List;

public interface CrudCollectionService<T extends BaseEntity> {
  List<T> createAll(List<T> entities);

  List<T> retrieveAll();

  List<T> updateAll(List<T> entities);

  void deleteAll(List<T> entities);
}
