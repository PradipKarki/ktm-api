package com.ktm.utils.mail.repository;

import com.ktm.utils.mail.model.EmailSubscriber;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailSubscriberRepository extends JpaRepository<EmailSubscriber, Long> {

  Optional<EmailSubscriber> findByEmailAddress(String emailAddress);

  List<EmailSubscriber> findByIsSubscribed(boolean isSubscribed);

  Optional<EmailSubscriber> findByVerifyToken(String token);

}
