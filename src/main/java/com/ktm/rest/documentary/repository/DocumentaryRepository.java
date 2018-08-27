package com.ktm.rest.documentary.repository;

import com.ktm.rest.documentary.model.Documentary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentaryRepository extends JpaRepository<Documentary, String> {
  /* no custom method */
}
