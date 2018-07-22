package com.ktm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such Item")
public class ResourceNotFoundException extends RuntimeException {

  private static final String ERROR_MSG = "The requested resource could not be found: ";
  private static final long serialVersionUID = 1L;

  public ResourceNotFoundException() {
    super(ERROR_MSG);
  }

  public ResourceNotFoundException(String message) {
    super(ERROR_MSG + message);
  }

  public ResourceNotFoundException(String message, Exception e) {
    super(message, e);
  }
}