package com.ktm.library.core.repository;

import com.ktm.library.core.model.RssNews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RssNewsRepository extends JpaRepository<RssNews, Long> {
  /* no custom method */
}
