package com.ktm.dictionary;

public enum Reference {
  INTERNATIONAL_RSS_NEWS_VALUES("NEWS-N01"),
  NATIONAL_RSS_NEWS_VALUES("NEWS-I01");

  public final String referenceTypeCode;

  Reference(String referenceTypeCode) {
    this.referenceTypeCode = referenceTypeCode;
  }

  public String getReferenceTypeCode() {
    return this.referenceTypeCode;
  }
}
