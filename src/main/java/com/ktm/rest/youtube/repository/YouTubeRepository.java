package com.ktm.rest.youtube.repository;

import com.ktm.rest.youtube.model.YouTubePo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface YouTubeRepository extends JpaRepository<YouTubePo, Long> {
  /* no custom method */
}
