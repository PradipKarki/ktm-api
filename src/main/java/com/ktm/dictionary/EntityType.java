package com.ktm.dictionary;

public enum EntityType {
  TWITTER(0),
  YOUTUBE(1),
  RSS_NEWS(2),
  DOCUMENTARY(3);

  /**
   * Value for this KtmSharableEntity
   */
  public final int value;

  EntityType(int value) {
    this.value = value;
  }
}
