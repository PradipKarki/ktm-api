package com.ktm.rest.mail.repository;

import com.ktm.rest.mail.model.EmailPo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<EmailPo, Long> {
  /* no custom method */
}
