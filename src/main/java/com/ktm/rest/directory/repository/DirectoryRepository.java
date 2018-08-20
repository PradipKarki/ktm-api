package com.ktm.rest.directory.repository;

import com.ktm.rest.directory.model.Directory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectoryRepository extends JpaRepository<Directory, Long> {
  /* no custom method */
}
