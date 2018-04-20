package com.ktm.utils.mail.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ktm.utils.mail.model.EmailPO;

public interface EmailRepository extends JpaRepository<EmailPO, Long> {
	/* no custom method */
}
