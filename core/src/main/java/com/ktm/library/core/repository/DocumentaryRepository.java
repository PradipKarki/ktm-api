package com.ktm.library.core.repository;

import com.ktm.library.core.model.Documentary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentaryRepository extends JpaRepository<Documentary, String> {
  /* no custom method */
}
