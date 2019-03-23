package com.ktm.library.core.dictionary;

import java.util.Arrays;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public enum EmailConfirmationStatus implements Dictionary<Integer, String> {
  CONFIRMED(0, "Confirmed"),
  INVALID_TOKEN(1, "Invalid Token"),
  CONFIRMATION_DATE_EXPIRED(2, "Token Expired"),
  ALREADY_CONFIRMED(3, "Confirmed Previous"),
  DEFAULT_VALUE(4, "");

  public final Integer code;
  public final String description;

  public static String getDescriptionFromCode(Integer key) {
    EmailConfirmationStatus emailConfirmationStatus =
        Arrays.stream(EmailConfirmationStatus.values())
            .filter(e -> Objects.equals(e.getCode(), key))
            .findFirst()
            .orElse(DEFAULT_VALUE);
    return emailConfirmationStatus.getDescription();
  }

  @Override
  public Integer getCode() {
    return this.code;
  }

  @Override
  public String getDescription() {
    return this.description;
  }
}
