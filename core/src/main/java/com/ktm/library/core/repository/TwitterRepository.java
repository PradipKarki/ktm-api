package com.ktm.library.core.repository;

import com.ktm.library.core.model.twitter.TwitterPo;
import com.ktm.library.core.model.twitter.TwitterUser;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TwitterRepository extends CrudRepository<TwitterPo, Long> {
  List<TwitterPo> findByTwitterUser(TwitterUser twitterUser);
}
