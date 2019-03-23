package com.ktm.library.core.utils;

import org.owasp.esapi.ESAPI;

public final class EncodeUtility {

  private EncodeUtility() {}

  public static String encodeLog(String text) {
    String encodedText = text.replace('\n', '_').replace('\r', '_').replace('\t', '_');
    return ESAPI.encoder().encodeForHTML(encodedText);
  }
}
