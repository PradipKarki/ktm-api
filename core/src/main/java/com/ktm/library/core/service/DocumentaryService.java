package com.ktm.library.core.service;

import com.google.api.services.youtube.model.Video;
import com.ktm.library.core.mapper.DocumentaryYouTubeVideoMapper;
import com.ktm.library.core.mapper.DtoConverter;
import com.ktm.library.core.model.Documentary;
import com.ktm.library.core.repository.DocumentaryRepository;
import java.util.List;
import java.util.Optional;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentaryService
    implements CrudService<Documentary, String>,
        CrudCollectionService<Documentary, String>,
        DtoConverter<Video, Documentary> {

  @Autowired private DocumentaryRepository documentaryRepository;

  @Override
  public Documentary create(Documentary documentary) {
    return documentaryRepository.save(documentary);
  }

  @Override
  public Optional<Documentary> find(String primaryKey) {
    return documentaryRepository.findById(primaryKey);
  }

  @Override
  public Documentary update(Documentary documentary) {
    return documentaryRepository.save(documentary);
  }

  @Override
  public void delete(Documentary documentary) {
    documentaryRepository.delete(documentary);
  }

  @Override
  public Iterable<Documentary> saveAll(Iterable<Documentary> documentaries) {
    return documentaryRepository.saveAll(documentaries);
  }

  @Override
  public Iterable<Documentary> findAll(Iterable<String> ids) {
    return documentaryRepository.findAllById(ids);
  }

  @Override
  public Iterable<Documentary> findAll() {
    return documentaryRepository.findAll();
  }

  @Override
  public Iterable<Documentary> updateAll(Iterable<Documentary> documentaries) {
    return documentaryRepository.saveAll(documentaries);
  }

  @Override
  public void deleteAll(Iterable<Documentary> documentaries) {
    documentaryRepository.deleteAll(documentaries);
  }

  @Override
  public List<Documentary> mapToDto(List<Video> apiEntities) {
    return Mappers.getMapper(DocumentaryYouTubeVideoMapper.class)
        .toYouTubeDocumentaryPo(apiEntities);
  }
}
