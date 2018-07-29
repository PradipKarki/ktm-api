package com.ktm.rest.mail.repository;

import com.ktm.rest.mail.model.EmailPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<EmailPo, Long> {
  /* no custom method */
}
