package com.ktm.library.core.dictionary;

import java.util.Arrays;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public enum Reference implements Dictionary<String, String> {
  INTERNATIONAL_RSS_NEWS_VALUES("NEWS-N01", "International RSS News List Code"),
  NATIONAL_RSS_NEWS_VALUES("NEWS-I01", "National RSS News List Code"),
  DEFAULT_VALUE("DEFAULT_VALUE", "");

  public final String referenceTypeCode;
  public final String referenceTypeDescription;

  public static String getDescriptionFromCode(String code) {
    Reference reference =
        Arrays.stream(Reference.values())
            .filter(e -> Objects.equals(e.getCode(), code))
            .findFirst()
            .orElse(DEFAULT_VALUE);
    return reference.getDescription();
  }

  @Override
  public String getCode() {
    return this.referenceTypeCode;
  }

  @Override
  public String getDescription() {
    return this.referenceTypeDescription;
  }
}
