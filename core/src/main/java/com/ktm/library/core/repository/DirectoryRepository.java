package com.ktm.library.core.repository;

import com.ktm.library.core.model.directory.Directory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectoryRepository extends JpaRepository<Directory, Long> {
  /* no custom method */
}
