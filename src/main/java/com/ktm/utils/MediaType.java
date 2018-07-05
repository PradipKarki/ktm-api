package com.ktm.utils;

public enum MediaType {
  TWITTER(0),
  YOUTUBE(1),
  RSS_NEWS(2),
  DOCUMENTARY(3);

  /**
   * Value for this KtmSharableEntity
   */
  public final int value;

  MediaType(int value) {
    this.value = value;
  }
}
