package com.ktm.library.core.exception;

public class ReferenceValueNotFoundException extends RuntimeException {
  private static final long serialVersionUID = 1L;
  private static final String ERROR_MSG =
      "Reference values could not be found for Reference Type Code: ";

  public ReferenceValueNotFoundException() {
    super(ERROR_MSG);
  }

  public ReferenceValueNotFoundException(String message) {
    super(ERROR_MSG + message);
  }

  public ReferenceValueNotFoundException(String message, Exception e) {
    super(message, e);
  }
}