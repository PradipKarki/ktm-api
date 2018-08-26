package com.ktm.dictionary;

import java.util.Arrays;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public enum EntityType implements Dictionary<Integer, String> {
  TWITTER(0, "twitter"),
  YOUTUBE(1, "you tube"),
  RSS_NEWS(2, "rss news"),
  DOCUMENTARY(3, "documentary"),
  DEFAULT_VALUE(4, "");

  public final Integer code;
  public final String description;

  public static String getDescriptionFromCode(Integer key) {
    EntityType entityType =
        Arrays.stream(EntityType.values())
            .filter(e -> Objects.equals(e.getCode(), key))
            .findFirst()
            .orElse(DEFAULT_VALUE);
    return entityType.getDescription();
  }

  @Override
  public Integer getCode() {
    return this.code;
  }

  @Override
  public String getDescription() {
    return this.description;
  }
}
