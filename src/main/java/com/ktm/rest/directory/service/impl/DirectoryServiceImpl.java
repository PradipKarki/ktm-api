package com.ktm.rest.directory.service.impl;

import com.ktm.rest.directory.model.Directory;
import com.ktm.rest.directory.repository.DirectoryRepository;
import com.ktm.rest.directory.service.DirectoryService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DirectoryServiceImpl implements DirectoryService {

  @Autowired private DirectoryRepository directoryRepository;

  @Override
  public Directory create(Directory directory) {
    return directoryRepository.save(directory);
  }

  @Override
  public Optional<Directory> read(Long primaryKey) {
    return directoryRepository.findById(primaryKey);
  }

  @Override
  public Directory update(Directory directory) {
    return directoryRepository.save(directory);
  }

  @Override
  public void delete(Directory directory) {
    directoryRepository.delete(directory);
  }

  @Override
  public List<Directory> createAll(List<Directory> directories) {
    return directoryRepository.saveAll(directories);
  }

  @Override
  public List<Directory> retrieveAll() {
    return directoryRepository.findAll();
  }

  @Override
  public List<Directory> updateAll(List<Directory> directories) {
    return directoryRepository.saveAll(directories);
  }

  @Override
  public void deleteAll(List<Directory> directories) {
    directoryRepository.deleteAll(directories);
  }
}
