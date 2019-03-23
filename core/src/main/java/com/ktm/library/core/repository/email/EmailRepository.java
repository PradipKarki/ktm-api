package com.ktm.library.core.repository.email;

import com.ktm.library.core.model.email.EmailPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<EmailPo, Long> {
  /* no custom method */
}
