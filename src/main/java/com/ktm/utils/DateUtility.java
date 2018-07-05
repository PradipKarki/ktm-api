package com.ktm.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateUtility {
  private DateUtility() {
  }

  public static LocalDate convertToLocalDate(Date dateToConvert) {
    return dateToConvert.toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();
  }

  public static LocalDateTime convertToLocalDateTime(Long longValue) {
    return Instant.ofEpochMilli(longValue)
                  .atZone(ZoneId.systemDefault())
                  .toLocalDateTime();
  }

  public static LocalDateTime convertToLocalDateTime(Date dateToConvert) {
    return dateToConvert.toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime();
  }
}
