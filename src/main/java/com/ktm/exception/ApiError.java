package com.ktm.exception;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import org.springframework.http.HttpStatus;

public class ApiError {

  private LocalDateTime timestamp;
  private HttpStatus status;
  private String message;
  private List<String> errors;

  public ApiError() {
  }

  public ApiError(HttpStatus status, String message,
      List<String> errors) {
    super();
    this.timestamp = LocalDateTime.now();
    this.status = status;
    this.message = message;
    this.errors = errors;
  }

  public ApiError(HttpStatus status, String message, String error) {
    super();
    this.timestamp = LocalDateTime.now();
    this.status = status;
    this.message = message;
    errors = Collections.singletonList(error);
  }

  public HttpStatus getStatus() {
    return status;
  }

  public void setStatus(HttpStatus status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public List<String> getErrors() {
    return errors;
  }

  public void setErrors(List<String> errors) {
    this.errors = errors;
  }

  public void setError(String error) {
    errors = Collections.singletonList(error);
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }
}