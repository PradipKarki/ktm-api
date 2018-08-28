package com.ktm.exception;

public class JobException extends RuntimeException {
  private static final long serialVersionUID = 1L;
  private static final String ERROR_MSG = "Something wrong in job: ";

  public JobException() {
    super(ERROR_MSG);
  }

  public JobException(String message) {
    super(ERROR_MSG + message);
  }

  public JobException(String message, Exception e) {
    super(message, e);
  }
}
