package com.ktm.rest.documentary.service.impl;

import com.ktm.rest.documentary.model.Documentary;
import com.ktm.rest.documentary.repository.DocumentaryRepository;
import com.ktm.rest.documentary.service.DocumentaryService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentaryServiceImpl implements DocumentaryService {

  @Autowired private DocumentaryRepository documentaryRepository;

  @Override
  public List<Documentary> createAll(List<Documentary> youTubePos) {
    return documentaryRepository.saveAll(youTubePos);
  }

  @Override
  public List<Documentary> retrieveAll() {
    return documentaryRepository.findAll();
  }

  @Override
  public List<Documentary> updateAll(List<Documentary> youTubePos) {
    return documentaryRepository.saveAll(youTubePos);
  }

  @Override
  public void deleteAll(List<Documentary> youTubePos) {
    documentaryRepository.deleteAll();
  }

  @Override
  public Documentary create(Documentary youTubePo) {
    return documentaryRepository.save(youTubePo);
  }

  @Override
  public Optional<Documentary> read(String primaryKey) {
    return documentaryRepository.findById(primaryKey);
  }

  @Override
  public Documentary update(Documentary youTubePo) {
    return documentaryRepository.save(youTubePo);
  }

  @Override
  public void delete(Documentary youTubePo) {
    documentaryRepository.delete(youTubePo);
  }
}
