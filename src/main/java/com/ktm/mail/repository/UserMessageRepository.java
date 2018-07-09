package com.ktm.mail.repository;

import com.ktm.mail.model.UserMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMessageRepository extends JpaRepository<UserMessage, Long> {
  /* no custom method */
}
