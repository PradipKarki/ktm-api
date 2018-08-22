package com.ktm.dictionary;

import java.util.Arrays;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public enum AddressType implements Dictionary<Integer, String> {
  BILLING(0, "Billing"),
  DELIVERY(1, "Delivery"),
  RESIDENTIAL(2, "Residential"),
  WORK(3, "Work"),
  DEFAULT_VALUE(40, "");

  public final Integer code;
  public final String description;

  @Override
  public Integer getCode() {
    return this.code;
  }

  @Override
  public String getDescription() {
    return this.description;
  }

  @Override
  public String getDescriptionFromCode(Integer key) {
    AddressType addressType =
        Arrays.stream(AddressType.values())
            .filter(b -> Objects.equals(b.getCode(), key))
            .findFirst()
            .orElse(DEFAULT_VALUE);
    return addressType.getDescription();
  }
}
