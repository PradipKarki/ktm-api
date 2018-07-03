package com.ktm.utils.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingController {

    @SuppressWarnings("static-method")
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> invalidInput(MethodArgumentNotValidException ex) {
        ExceptionResponse response = new ExceptionResponse("Validation Error", HttpStatus.BAD_REQUEST, //$NON-NLS-1$
                ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @SuppressWarnings("static-method")
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> assertionException(IllegalArgumentException ex) {
        ExceptionResponse response = new ExceptionResponse("Invalid Inputs", HttpStatus.UNPROCESSABLE_ENTITY, //$NON-NLS-1$
                ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
    }
/*
	@ExceptionHandler(Throwable.class)
	public ResponseEntity<ExceptionResponse> handleThrowable(final Throwable ex) {
		ExceptionResponse response = new ExceptionResponse("An unexpected internal server error occurred", //$NON-NLS-1$
				HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}*/
}
