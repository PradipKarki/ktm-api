package com.ktm.utils;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

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

  private static String switchDemo(Cover type) {
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

  @Test
  public void stringIsUnicodeTest() {
    Assert.assertTrue(TextUtility.isThisUnicode("dfdfऑfdfdf", Character.UnicodeBlock.DEVANAGARI));
    Assert.assertFalse(TextUtility.isThisUnicode("dfdffdfdf", Character.UnicodeBlock.DEVANAGARI));
  }

  @Test
  public void randomTest() {
    String str = "00000XYS";
    String newVal = StringUtils.stripStart(str, "0");
    Assert.assertEquals("XYS", newVal);
    Assert.assertThat("cover", is(equalTo(switchDemo(type))));
  }
}
