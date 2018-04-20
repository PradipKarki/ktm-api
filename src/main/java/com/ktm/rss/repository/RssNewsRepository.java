package com.ktm.rss.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ktm.utils.mail.model.EmailPO;

public interface RssNewsRepository extends JpaRepository<EmailPO, Long> {
	/* no custom method */
}
