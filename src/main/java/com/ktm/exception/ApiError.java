package com.ktm.exception;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import org.springframework.http.HttpStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
class ApiError {

  private LocalDateTime timestamp;
  private HttpStatus status;
  private String message;
  private List<String> errors;

  ApiError(HttpStatus status, String message, List<String> errors) {
    this(status, message);
    this.errors = errors;
  }

  ApiError(HttpStatus status, String message, String error) {
    this(status, message);
    errors = Collections.singletonList(error);
  }

  private ApiError(HttpStatus status, String message) {
    this.timestamp = LocalDateTime.now();
    this.status = status;
    this.message = message;
  }
}
