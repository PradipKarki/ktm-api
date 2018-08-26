package com.ktm.dictionary;

import java.util.Arrays;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public enum BusinessCategory implements Dictionary<Integer, String> {
  SPORTS_TEAM_AND_CLUBS(0, "Sports Teams & Clubs"),
  DEFAULT_VALUE(40, "");

  public final Integer code;
  public final String description;

  public static String getDescriptionFromCode(Integer key) {
    BusinessCategory businessCategory =
        Arrays.stream(BusinessCategory.values())
            .filter(b -> Objects.equals(b.getCode(), key))
            .findFirst()
            .orElse(DEFAULT_VALUE);
    return businessCategory.getDescription();
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
