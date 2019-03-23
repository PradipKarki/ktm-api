package com.ktm.library.core.service;

import com.ktm.library.core.model.directory.Directory;
import com.ktm.library.core.repository.DirectoryRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DirectoryService
    implements CrudService<Directory, Long>, CrudCollectionService<Directory, Long> {

  @Autowired private DirectoryRepository directoryRepository;

  @Override
  public Directory create(Directory directory) {
    return directoryRepository.save(directory);
  }

  @Override
  public Optional<Directory> find(Long primaryKey) {
    return directoryRepository.findById(primaryKey);
  }

  @Override
  public Directory update(Directory directory) {
    return directoryRepository.save(directory);
  }

  @Override
  public void delete(Directory entity) {
    directoryRepository.delete(entity);
  }

  @Override
  public Iterable<Directory> saveAll(Iterable<Directory> directories) {
    return directoryRepository.saveAll(directories);
  }

  @Override
  public Iterable<Directory> findAll(Iterable<Long> ids) {
    return directoryRepository.findAllById(ids);
  }

  @Override
  public Iterable<Directory> findAll() {
    return directoryRepository.findAll();
  }

  @Override
  public Iterable<Directory> updateAll(Iterable<Directory> directories) {
    return directoryRepository.saveAll(directories);
  }

  @Override
  public void deleteAll(Iterable<Directory> directories) {
    directoryRepository.deleteAll(directories);
  }
}
