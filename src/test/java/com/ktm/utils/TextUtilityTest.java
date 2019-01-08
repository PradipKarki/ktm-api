package com.ktm.utils;

import java.util.Collections;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

enum Cover {
  BIGCOVER,
  COVER,
  OTHER
}

public class TextUtilityTest {
  private Cover type = Cover.COVER;
  private String switchVal = switchDemo(type);

  @Test
  public void isThisUnicode() {
    Assert.assertTrue(TextUtility.isThisUnicode("dfdfऑfdfdf", Character.UnicodeBlock.DEVANAGARI));
    Assert.assertFalse(TextUtility.isThisUnicode("dfdffdfdf", Character.UnicodeBlock.DEVANAGARI));
  }

  @Test
  public void randomTest() {
    String str = "00000XYS";
    String newVal = StringUtils.stripStart(str, "0");
    Assert.assertEquals("XYS", newVal);

    int a[] = new int[] {30, 100, 33, 0, 0, 0, 0, 0, 0, 0};
    System.out.println(Collections.singletonList(a).size());
    System.out.println(switchVal);
  }

  private String switchDemo(Cover type) {
    Objects.requireNonNull(type, "Cover type must not be null");
    switch (type) {
      case BIGCOVER:
        return "big_cover";
      case COVER:
        return "cover";
      default:
        return "other";
    }
  }
}
