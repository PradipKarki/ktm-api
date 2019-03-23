package com.ktm.library.core.repository.email;

import com.ktm.library.core.model.email.EmailSubscriber;
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
