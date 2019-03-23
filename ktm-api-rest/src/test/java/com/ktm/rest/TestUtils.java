package com.ktm.rest;

public interface TestUtils {

  static IllegalStateException newIllegalStateException() {
    return new IllegalStateException("empty stream");
  }
}
