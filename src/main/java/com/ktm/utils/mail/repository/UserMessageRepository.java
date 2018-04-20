package com.ktm.utils.mail.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ktm.utils.mail.model.UserMessage;

public interface UserMessageRepository extends JpaRepository<UserMessage, Long> {
		/* no custom method */
}
