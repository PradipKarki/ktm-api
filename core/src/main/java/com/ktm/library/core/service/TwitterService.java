package com.ktm.library.core.service;

import com.ktm.library.core.mapper.DtoConverter;
import com.ktm.library.core.mapper.TwitterMapper;
import com.ktm.library.core.model.twitter.TwitterPo;
import com.ktm.library.core.repository.TwitterRepository;
import java.util.List;
import java.util.Optional;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter4j.Status;

@Service
public class TwitterService
    implements CrudService<TwitterPo, Long>,
        CrudCollectionService<TwitterPo, Long>,
        DtoConverter<Status, TwitterPo> {
  @Autowired private TwitterRepository twitterRepository;

  @Override
  public TwitterPo create(TwitterPo entity) {
    return twitterRepository.save(entity);
  }

  @Override
  public Optional<TwitterPo> find(Long id) {
    return twitterRepository.findById(id);
  }

  @Override
  public TwitterPo update(TwitterPo entity) {
    return twitterRepository.save(entity);
  }

  @Override
  public void delete(TwitterPo entity) {
    twitterRepository.delete(entity);
  }

  @Override
  public List<TwitterPo> mapToDto(List<Status> apiEntities) {
    return Mappers.getMapper(TwitterMapper.class).toTwitterPo(apiEntities);
  }

  @Override
  public Iterable<TwitterPo> saveAll(Iterable<TwitterPo> entities) {
    return twitterRepository.saveAll(entities);
  }

  @Override
  public Iterable<TwitterPo> findAll(Iterable<Long> ids) {
    return twitterRepository.findAllById(ids);
  }

  @Override
  public Iterable<TwitterPo> findAll() {
    return twitterRepository.findAll();
  }

  @Override
  public void deleteAll(Iterable<TwitterPo> entities) {
    twitterRepository.deleteAll(entities);
  }

  @Override
  public Iterable<TwitterPo> updateAll(Iterable<TwitterPo> entities) {
    return twitterRepository.saveAll(entities);
  }
}
