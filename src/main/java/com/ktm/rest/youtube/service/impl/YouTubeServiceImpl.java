package com.ktm.rest.youtube.service.impl;

import com.ktm.rest.youtube.model.YouTubePo;
import com.ktm.rest.youtube.repository.YouTubeRepository;
import com.ktm.rest.youtube.service.YouTubeService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class YouTubeServiceImpl implements YouTubeService {

  @Autowired private YouTubeRepository youTubeRepository;

  @Override
  public List<YouTubePo> createAll(List<YouTubePo> youTubePos) {
    return youTubeRepository.saveAll(youTubePos);
  }

  @Override
  public List<YouTubePo> retrieveAll() {
    return youTubeRepository.findAll();
  }

  @Override
  public List<YouTubePo> updateAll(List<YouTubePo> youTubePos) {
    return youTubeRepository.saveAll(youTubePos);
  }

  @Override
  public void deleteAll(List<YouTubePo> youTubePos) {
    youTubeRepository.deleteAll();
  }

  @Override
  public YouTubePo create(YouTubePo youTubePo) {
    return youTubeRepository.save(youTubePo);
  }

  @Override
  public Optional<YouTubePo> read(String primaryKey) {
    return youTubeRepository.findById(primaryKey);
  }

  @Override
  public YouTubePo update(YouTubePo youTubePo) {
    return youTubeRepository.save(youTubePo);
  }

  @Override
  public void delete(YouTubePo youTubePo) {
    youTubeRepository.delete(youTubePo);
  }
}
