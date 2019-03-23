package com.ktm.library.core.service;

import com.google.api.services.youtube.model.SearchResult;
import com.ktm.library.core.mapper.DtoConverter;
import com.ktm.library.core.mapper.YouTubeSearchMapper;
import com.ktm.library.core.model.YouTubePo;
import com.ktm.library.core.repository.YouTubeRepository;
import java.util.List;
import java.util.Optional;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class YouTubeService
    implements CrudService<YouTubePo, String>,
        CrudCollectionService<YouTubePo, String>,
        DtoConverter<SearchResult, YouTubePo> {

  @Autowired private YouTubeRepository youTubeRepository;

  @Override
  public YouTubePo create(YouTubePo youTubePo) {
    return youTubeRepository.save(youTubePo);
  }

  @Override
  public Optional<YouTubePo> find(String primaryKey) {
    return youTubeRepository.findById(primaryKey);
  }

  @Override
  public YouTubePo update(YouTubePo youTubePo) {
    return youTubeRepository.save(youTubePo);
  }

  @Override
  public void delete(YouTubePo entity) {
    youTubeRepository.delete(entity);
  }

  @Override
  public Iterable<YouTubePo> saveAll(Iterable<YouTubePo> directories) {
    return youTubeRepository.saveAll(directories);
  }

  @Override
  public Iterable<YouTubePo> findAll(Iterable<String> ids) {
    return youTubeRepository.findAllById(ids);
  }

  @Override
  public Iterable<YouTubePo> findAll() {
    return youTubeRepository.findAll();
  }

  @Override
  public Iterable<YouTubePo> updateAll(Iterable<YouTubePo> directories) {
    return youTubeRepository.saveAll(directories);
  }

  @Override
  public void deleteAll(Iterable<YouTubePo> directories) {
    youTubeRepository.deleteAll(directories);
  }

  @Override
  public List<YouTubePo> mapToDto(List<SearchResult> apiEntities) {
    return Mappers.getMapper(YouTubeSearchMapper.class).toYouTubePo(apiEntities);
  }
}
