package com.ktm.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.Nonnull;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingController extends ResponseEntityExceptionHandler {

  private static List<String> getFieldAndObjectError(BindingResult bindingResult) {
    List<String> errors = new ArrayList<>();
    for (FieldError error : bindingResult.getFieldErrors()) {
      errors.add(error.getField() + ": " + error.getDefaultMessage());
    }
    for (ObjectError error : bindingResult.getGlobalErrors()) {
      errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
    }
    return errors;
  }

  /* error 400 -> Validation Error */
  @Override
  @Nonnull
  public ResponseEntity<Object> handleMethodArgumentNotValid(
      @Nonnull MethodArgumentNotValidException ex, @Nonnull HttpHeaders headers,
      @Nonnull HttpStatus status,
      @Nonnull WebRequest request) {
    List<String> errors = getFieldAndObjectError(ex.getBindingResult());
    ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex
        .getLocalizedMessage(), errors);
    return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
  }

  /* error 422 */
  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<Object> assertionException(IllegalArgumentException ex) {
    ApiError apiError = new ApiError(HttpStatus.UNPROCESSABLE_ENTITY, ex
        .getLocalizedMessage(), "Invalid Inputs");
    return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
  }

  @Override
  @Nonnull
  public ResponseEntity<Object> handleBindException(@Nonnull BindException ex,
      @Nonnull HttpHeaders headers, @Nonnull HttpStatus status, @Nonnull WebRequest request) {
    List<String> errors = getFieldAndObjectError(ex.getBindingResult());
    ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex
        .getLocalizedMessage(), errors);
    return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
  }

  @Override
  @Nonnull
  public ResponseEntity<Object> handleTypeMismatch(@Nonnull TypeMismatchException ex,
      @Nonnull HttpHeaders headers, @Nonnull HttpStatus status, @Nonnull WebRequest request) {
    String error = ex.getValue() + " value for " + ex
        .getPropertyName() + " should be of type " + ex.getRequiredType();

    ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
    return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
  }

  @Override
  @Nonnull
  public ResponseEntity<Object> handleMissingServletRequestPart(
      @Nonnull MissingServletRequestPartException ex, @Nonnull HttpHeaders headers,
      @Nonnull HttpStatus status, @Nonnull WebRequest request) {
    String error = ex.getRequestPartName() + " part is missing";
    ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
    return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
  }

  @Override
  @Nonnull
  public ResponseEntity<Object> handleMissingServletRequestParameter(
      @Nonnull MissingServletRequestParameterException ex, @Nonnull HttpHeaders headers,
      @Nonnull HttpStatus status, @Nonnull WebRequest request) {
    String error = ex.getParameterName() + " parameter is missing";
    ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
    return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
      MethodArgumentTypeMismatchException ex) {
    String error = ex.getName() + " should be of type " + Objects
        .requireNonNull(ex.getRequiredType()).getName();

    ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
    return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
    List<String> errors = new ArrayList<>();
    for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
      errors.add(violation.getRootBeanClass().getName() + " " + violation
          .getPropertyPath() + ": " + violation.getMessage());
    }

    ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex
        .getLocalizedMessage(), errors);
    return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
  }

  /* error 404 */
  @Override
  @Nonnull
  public ResponseEntity<Object> handleNoHandlerFoundException(
      @Nonnull NoHandlerFoundException ex, @Nonnull HttpHeaders headers, @Nonnull HttpStatus status,
      @Nonnull WebRequest request) {
    String error = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();

    ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getLocalizedMessage(), error);
    return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
  }

  /* error 404 */
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException
      ex) {
    ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex
        .getMessage(), "No such Item");
    return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
  }

  /* error 405 */
  @Override
  @Nonnull
  public ResponseEntity<Object> handleHttpRequestMethodNotSupported(
      @Nonnull HttpRequestMethodNotSupportedException ex, @Nonnull HttpHeaders headers,
      @Nonnull HttpStatus status, @Nonnull WebRequest request) {
    StringBuilder builder = new StringBuilder();
    builder.append(ex.getMethod());
    builder.append(" method is not supported for this request. Supported methods are ");
    Objects.requireNonNull(ex.getSupportedHttpMethods())
           .forEach(t -> builder.append(t).append(" "));

    ApiError apiError = new ApiError(HttpStatus.METHOD_NOT_ALLOWED, ex
        .getLocalizedMessage(), builder.toString());
    return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
  }

  /* error 415 */
  @Override
  @Nonnull
  public ResponseEntity<Object> handleHttpMediaTypeNotSupported(
      @Nonnull HttpMediaTypeNotSupportedException ex, @Nonnull HttpHeaders headers,
      @Nonnull HttpStatus status, @Nonnull WebRequest request) {
    StringBuilder builder = new StringBuilder();
    builder.append(ex.getContentType());
    builder.append(" media type is not supported. Supported media types are ");
    ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(" "));

    ApiError apiError = new ApiError(HttpStatus.UNSUPPORTED_MEDIA_TYPE, ex
        .getLocalizedMessage(), builder.substring(0, builder.length() - 2));
    return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
  }

  /* error 500 */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleAll(Throwable ex) {
    ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex
        .getLocalizedMessage(), "An unexpected internal server error occurred");
    return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
  }

}