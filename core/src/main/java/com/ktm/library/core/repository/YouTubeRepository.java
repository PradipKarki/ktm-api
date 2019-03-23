package com.ktm.library.core.repository;

import com.ktm.library.core.model.YouTubePo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YouTubeRepository extends JpaRepository<YouTubePo, String> {
  /* no custom method */
}
