package com.ktm.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

public class DateUtility {
  private DateUtility() {
  }

  public static LocalDate convertToLocalDate(Date dateToConvert) {
    if (dateToConvert == null) {
      return null;
    }
    return dateToConvert.toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();
  }

  public static LocalDateTime convertToLocalDateTime(Long longValue) {
    if (Objects.isNull(longValue)) {
      return null;
    }
    return Instant.ofEpochMilli(longValue)
                  .atZone(ZoneId.systemDefault())
                  .toLocalDateTime();
  }

  public static LocalDateTime convertToLocalDateTime(Date dateToConvert) {
    if (dateToConvert == null) {
      return null;
    }
    return dateToConvert.toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime();
  }
}
