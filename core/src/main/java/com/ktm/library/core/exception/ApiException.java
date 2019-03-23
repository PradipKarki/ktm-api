package com.ktm.library.core.exception;

import static java.text.MessageFormat.format;

public class ApiException extends RuntimeException {
  private static final long serialVersionUID = 1L;
  private static final String ERROR_MSG = "Error while getting data from API: {0}";

  public ApiException() {
    super(ERROR_MSG);
  }

  public ApiException(String message) {
    super(format(ERROR_MSG, message));
  }

  public ApiException(String message, Exception e) {
    super(format(ERROR_MSG, message), e);
  }
}
