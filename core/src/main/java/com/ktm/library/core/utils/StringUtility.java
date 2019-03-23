package com.ktm.library.core.utils;

import static com.ktm.library.core.utils.CoreApiConstants.SEPARATOR;

import java.util.Arrays;
import java.util.List;

public final class StringUtility {

  private StringUtility() {}

  public static String listToString(List<String> list) {
    return String.join(SEPARATOR, list);
  }

  public static List<String> stringToList(String str, String regex) {
    return Arrays.asList(str.split(regex));
  }
}
