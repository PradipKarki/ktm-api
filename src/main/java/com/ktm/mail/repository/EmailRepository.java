package com.ktm.mail.repository;

import com.ktm.mail.model.EmailPO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<EmailPO, Long> {
  /* no custom method */
}
