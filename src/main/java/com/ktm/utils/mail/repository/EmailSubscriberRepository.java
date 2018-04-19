package com.ktm.utils.mail.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ktm.utils.mail.model.EmailSubscriber;

public interface EmailSubscriberRepository extends JpaRepository<EmailSubscriber, Long> {

    public Optional<EmailSubscriber> findByEmailAddress(String emailAddress);

	public List<EmailSubscriber> findByIsSubscribed(boolean isSubscribed);

}
