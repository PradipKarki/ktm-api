package com.ktm.library.core.repository.email;

import com.ktm.library.core.model.email.UserMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMessageRepository extends JpaRepository<UserMessage, Long> {
  /* no custom method */
}
