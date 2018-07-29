package com.ktm.rest.mail.repository;

import com.ktm.rest.mail.model.EmailSubscriber;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailSubscriberRepository extends JpaRepository<EmailSubscriber, Long> {

  Optional<EmailSubscriber> findByEmailAddress(String emailAddress);

  List<EmailSubscriber> findByIsSubscribed(boolean isSubscribed);

  Optional<EmailSubscriber> findByVerificationToken(String token);

}
