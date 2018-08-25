package com.ktm.rest.twitter.service.impl;

import com.ktm.rest.twitter.model.TwitterPo;
import com.ktm.rest.twitter.repository.TwitterRepository;
import com.ktm.rest.twitter.service.TwitterService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TwitterServiceImpl implements TwitterService {

  @Autowired private TwitterRepository twitterRepository;

  @Override
  public TwitterPo create(TwitterPo twitterPo) {
    return twitterRepository.save(twitterPo);
  }

  @Override
  public Optional<TwitterPo> read(Long primaryKey) {
    return twitterRepository.findById(primaryKey);
  }

  @Override
  public TwitterPo update(TwitterPo twitterPo) {
    return twitterRepository.save(twitterPo);
  }

  @Override
  public void delete(TwitterPo twitterPo) {
    twitterRepository.delete(twitterPo);
  }

  @Override
  public List<TwitterPo> createAll(List<TwitterPo> twitterPos) {
    return twitterRepository.saveAll(twitterPos);
  }

  @Override
  public List<TwitterPo> retrieveAll() {
    return twitterRepository.findAll();
  }

  @Override
  public List<TwitterPo> updateAll(List<TwitterPo> twitterPos) {
    return twitterRepository.saveAll(twitterPos);
  }

  @Override
  public void deleteAll(List<TwitterPo> twitterPos) {
    twitterRepository.deleteAll(twitterPos);
  }
}
