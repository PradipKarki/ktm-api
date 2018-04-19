package com.ktm.twitter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ktm.twitter.model.TwitterPO;
import com.ktm.twitter.model.TwitterUser;

@Repository
public interface TwitterRepository extends JpaRepository<TwitterPO, Long> {

	List<TwitterPO> findByTwitterUser(TwitterUser twitterUser);

}
