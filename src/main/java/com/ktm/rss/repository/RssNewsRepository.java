package com.ktm.rss.repository;

import com.ktm.utils.mail.model.EmailPO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RssNewsRepository extends JpaRepository<EmailPO, Long> {
    /* no custom method */
}
