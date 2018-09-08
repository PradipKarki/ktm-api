package com.ktm.rest.job.twitter.service.impl;

import static java.lang.Character.UnicodeBlock.DEVANAGARI;
import static java.util.stream.Collectors.toList;

import com.ktm.utils.TextUtility;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TwitterApiServiceImplTest {
  List<String> modifiableList;

  // check if title contains middle text or first three words of title or last three words of title
  private static Predicate<String> containsSimilarWords(String[] splitStr, String titleSubString) {
    return title ->
        title.contains(titleSubString)
            || TextUtility.containsFirstThreeWords(title, splitStr)
            || TextUtility.containsLastThreeWords(title, splitStr);
  }

  private static boolean isStringDuplicateOrSimilar(List<String> list, String title) {
    String[] splitStr = title.split(StringUtils.SPACE);
    String titleSubString = TextUtility.extractMiddleText(title);
    long count = list.stream().filter(containsSimilarWords(splitStr, titleSubString)).count();
    return list.removeIf(t -> t.equals(title) && count > 1L);
  }

  @Before
  public void initialize() {
    modifiableList = new ArrayList<>();
    modifiableList.add("not same words test 1 xyz not same words");
    modifiableList.add("not difference same words test 1 xyz not not same words");
    modifiableList.add("first threes words test 1 xyz not same words");
    modifiableList.add("first three words test 2 xyz last three words");
    modifiableList.add("first three words test 3 xyz last three words");
    modifiableList.add("first three words Test 4 xyz last three words");
    modifiableList.add("different words Test 5 xyz last different words");
  }

  @Test
  public void processDataTest() {
    List<String> filteredList =
        new ArrayList<>(modifiableList)
            .stream()
            .filter(StringUtils::isNotEmpty)
            .filter(title -> !TextUtility.isThisUnicode(title, DEVANAGARI))
            .filter(title -> !isStringDuplicateOrSimilar(modifiableList, title))
            .collect(toList());
    Assert.assertEquals(2, filteredList.size());
    Assert.assertArrayEquals(
        filteredList.toArray(),
        new String[] {
          "first threes words test 1 xyz not same words",
          "different words Test 5 xyz last different words"
        });
  }
}
