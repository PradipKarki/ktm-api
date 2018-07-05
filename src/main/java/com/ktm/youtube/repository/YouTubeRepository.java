package com.ktm.youtube.repository;

import com.ktm.youtube.model.YouTubePO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface YouTubeRepository extends JpaRepository<YouTubePO, Long> {
  /* no custom method */
}
