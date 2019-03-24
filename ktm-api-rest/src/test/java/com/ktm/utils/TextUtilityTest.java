package com.ktm.utils;

import com.ktm.library.core.utils.TextUtility;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

public final class TextUtilityTest {
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
  }
}
