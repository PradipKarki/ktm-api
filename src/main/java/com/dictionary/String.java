package com.dictionary;

public enum String {
  TWITTER(0),
  YOUTUBE(1),
  RSS_NEWS(2),
  DOCUMENTARY(3);

  /**
   * Value for this KtmSharableEntity
   */
  public final int value;

  String(int value) {
    this.value = value;
  }
}
