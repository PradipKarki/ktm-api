package com.ktm.utils;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

public class TextUtilityTest {

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
  }
}
