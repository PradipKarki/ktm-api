package com.ktm.twitter.mapper;

import com.ktm.twitter.model.TwitterPo;
import twitter4j.Status;

//@Mapper
public interface TwitterMapper {

  TwitterPo toTwitterPo(Status status);

}
