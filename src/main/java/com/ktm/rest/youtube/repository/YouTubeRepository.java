package com.ktm.rest.youtube.repository;

import com.ktm.rest.youtube.model.YouTubePo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YouTubeRepository extends JpaRepository<YouTubePo, String> {
  /* no custom method */
}
