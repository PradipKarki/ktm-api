package com.ktm.utils.exception;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;

public class ExceptionResponse {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final String message;
    private final HttpStatus errorCode;
    private final String details;

    public ExceptionResponse(String message, HttpStatus errorCode, String details) {
        super();
        this.message = message;
        this.errorCode = errorCode;
        this.details = details;
    }

    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }

    public String getMessage() {
        return this.message;
    }

    public HttpStatus getErrorCode() {
        return this.errorCode;
    }

    public String getDetails() {
        return this.details;
    }

}