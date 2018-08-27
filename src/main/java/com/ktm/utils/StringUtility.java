package com.ktm.utils;

import java.util.Arrays;
import java.util.List;

public final class StringUtility {

  public static final String SEPARATOR = ",";

  private StringUtility() {}

  public static String listToString(List<String> list) {
    return String.join(SEPARATOR, list);
  }

  public static List<String> stringToList(String str, String regex) {
    return Arrays.asList(str.split(regex));
  }
}
