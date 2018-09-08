package com.ktm.utils;

import java.util.Arrays;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

enum Cover {
  BIGCOVER,
  COVER,
  OTHER;
}

public class TextUtilityTest {
  Cover type = null;
  String switchVal = switchDemo(type);

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

    int a[] = new int[10];
    a[0] = 30;
    a[1] = 100;
    a[2] = 33;
    System.out.println((Arrays.asList(a)).size());
    System.out.println(switchVal);
  }

  public String switchDemo(Cover type) {
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
