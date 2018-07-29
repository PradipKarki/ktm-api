package com.ktm.rest.mail.repository;

import com.ktm.rest.mail.model.UserMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMessageRepository extends JpaRepository<UserMessage, Long> {
  /* no custom method */
}
