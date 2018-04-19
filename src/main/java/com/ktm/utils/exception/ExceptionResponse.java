package com.ktm.utils.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;

public class ExceptionResponse {
	private Date timestamp = new Date();
	private String message;
	private HttpStatus errorCode;
	private String details;

	public ExceptionResponse(String message, HttpStatus errorCode, String details) {
		super();
		this.message = message;
		this.errorCode = errorCode;
		this.details = details;
	}

	public Date getTimestamp() {
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