package com.ktm.rest.rss.repository;

import com.ktm.rest.rss.model.RssNews;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RssNewsRepository extends JpaRepository<RssNews, Long> {
  /* no custom method */
}
